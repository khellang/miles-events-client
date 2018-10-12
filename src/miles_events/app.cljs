(ns miles-events.app
  (:require
    [miles-events.routes.subs :as route-subs]
    [miles-events.material-ui.core :as ui]
    [miles-events.routes.core :as routes]
    [re-frame.core :as rf]
    [reagent.core :as r]))

(defmulti render-view identity)

(defmethod render-view :home []
  (fn []
    [:h1 "Home"]))

(defmethod render-view :about []
  (fn []
    [:h1 "About"]))

(def theme
  (ui/create-mui-theme
    {:palette {:type      :light
               :primary   {:main "#b12f2a"}
               :secondary {:main "#0c2338"}}}))

(defn styles [theme]
  {:app-bar       {:position :relative}
   :toolbar-title {:flex 1}})

(defn render []
  [(ui/with-styles styles
     (fn [{:keys [classes]}]
       (let [view @(rf/subscribe [::route-subs/active-view])]
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
             {:href (routes/url-for ::routes/home)
              :color :inherit}
             "Home"]
            [ui/button
             {:href (routes/url-for ::routes/about)
              :color :inherit}
             "About"]]]
          [(render-view view)]])))])
