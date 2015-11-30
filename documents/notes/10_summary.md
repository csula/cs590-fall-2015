# Course Wrap Up

## Session Management

Consider the following concept about sessions:

* Store sessions so that a user can be identified when he/she returns to the website
* Session is managed by the browser and does not really work with `microservices`
* Session is wrapped around cookies and can easily be "stolen" so care must be taken when developing session code

### Example of session management

First, create a new luminus project with the command:

`lein new luminus session-demo`

Next, update `handler.clj` so the new session route is added:

```clojure
[session-demo.routes.home :refer [home-routes app-session-routes]]
```

And also these routes are included in `app-routes` definition

```clojure
(def app-routes
  (routes
    (wrap-routes #'app-session-routes middleware/wrap-base) ;; add this line
    (wrap-routes #'home-routes middleware/wrap-csrf)
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))
``` 

Now, update the `middleware.clj` so that it contains the `cookie-store` dependency

```clojure
[ring.middleware.session.cookie :refer [cookie-store]] 
```

Update `wrap-base` function:

```clojure
(defn wrap-base [handler]
  (-> ((:middleware defaults) handler)
      wrap-formats
      wrap-webjars
      wrap-flash
      (wrap-session {:cookie-attrs {:http-only true}})
      (wrap-defaults
        (-> site-defaults
            (assoc-in [:security :anti-forgery] false)
            (assoc-in [:session :store] (cookie-store {:key "BuD3KgdAXhDHrJXu"}))
            (assoc-in [:session :cookie-name] "example-app-sessions")))
      wrap-context
      wrap-internal-error))
```

Finally, modify `home.clj` by first adding to the `:require` 

```clojure
[ring.util.response :refer [response]]
```

and add a few functions and routes to support our session management:

```clojure
(defn get-user [{session :session}]
  (response (str session)))

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defn set-user! [id {session :session}]
  (-> (str "User set to: " id)
      response
      (assoc :session (assoc session
                        :user id
                        :token (uuid)))))

(defn remove-user! [{session :session}]
  (-> (response "")
      (assoc :session (dissoc session :user :token))))

(defn clear-session! []
  (dissoc (response "") :session))

(defroutes app-session-routes
  (GET "/get-session" req (get-user req))
  (GET "/login/:id" [id :as req] (set-user! id req))
  (GET "/remove" req (remove-user! req))
  (GET "/logout" req (clear-session!)))
```

You should be able to use `postman` now to simulate `login`, `logout`, and `get-session` values.  

For your final project you should probably move all of the above into a separate session management file called `session.clj` and in this file you would expose the various session functions.
