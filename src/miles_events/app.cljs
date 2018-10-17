(ns miles-events.app
  (:require
    [camel-snake-kebab.core :refer [->kebab-case-keyword]]
    [camel-snake-kebab.extras :refer [transform-keys]]
    [miles-events.routes.subs :as route-subs]
    [miles-events.material-ui.icons :as icons]
    [miles-events.material-ui.core :as ui]
    [miles-events.routes.core :as routes]
    [miles-events.events :as events]
    [miles-events.subs :as subs]
    [taoensso.timbre :as timbre]
    [re-frame.core :as rf]
    [reagent.core :as r]
    [google-login]))

(def google-login (r/adapt-react-class js/GoogleLogin))

(defmulti render-view identity)

(defmethod render-view :home []
  [:main
   [:h1 [icons/home] " Home"]
   [google-login {:client-id     "618888275073-4mat6pbe42e0lvk5pt4bpqt17vf308dm.apps.googleusercontent.com"
                  :hosted-domain "miles.no"
                  :on-success    (fn [result]
                                   (let [profile (->> result
                                                   (.-profileObj)
                                                   (js->clj)
                                                   (transform-keys ->kebab-case-keyword))
                                         token (.-tokenId result)]
                                     (rf/dispatch [::events/login-success (assoc profile :token token)])))
                  :on-failure    #(timbre/error %)}
    [ui/typography
     {:variant :button
      :color   :inherit}
     "Login with Google"]]])

(defmethod render-view :about []
  [:h1 "About"])

(def theme
  (ui/create-mui-theme
    {:typography {:use-next-variants true}
     :palette    {:type      :light
                  :primary   {:main "#b12f2a"}
                  :secondary {:main "#0c2338"}}}))

(defn styles [theme]
  {:app-bar       {:position :relative}
   :avatar        {:margin 10}
   :toolbar-title {:flex 1}})

(def render
  (ui/with-styles styles
    (fn [{:keys [classes]}]
      (let [view (rf/subscribe [::route-subs/active-view])
            user (rf/subscribe [::subs/user])]
        [ui/mui-theme-provider
         {:theme theme}
         [ui/css-baseline]
         [ui/app-bar
          {:position :static
           :class    (:app-bar classes)}
          [ui/toolbar
           [ui/typography
            {:variant :h6
             :color   :inherit
             :no-wrap true
             :class   (:toolbar-title classes)}
            "Miles Events"]
           [ui/button
            {:href  (routes/url-for ::routes/home)
             :color :inherit}
            "Home"]
           [ui/button
            {:href  (routes/url-for ::routes/about)
             :color :inherit}
            "About"]
           (when @user
             [ui/avatar
              {:alt   (:name @user)
               :class (:avatar classes)
               :src   (:image-url @user)}])]]
         (render-view @view)]))))
