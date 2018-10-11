(ns miles-events.app
  (:require
    [miles-events.routes.subs :as route-subs]
    [miles-events.semantic-ui :as ui]
    [miles-events.routes.core :as routes]
    [re-frame.core :as rf]
    [reagent.core :as r]))

(defmulti render-view identity)

(defn label []
  (fn []
    [ui/label
     {:color  :yellow
      :ribbon true}
     [ui/icon {:name :star}]
     "Awesome"]))

(defmethod render-view :home []
  (fn []
    [:<>
     [ui/header {:as :h1} "Events"]
     [ui/divider {:hidden true}]
     [ui/card-group
      {:items-per-row 3
       :stackable     true}
      (map
        (fn [index]
          ^{:key index}
          [ui/card {:link true}
           [ui/image
            (merge
              {:src   "http://placehold.it/300x200"
               :fluid true}
              (when (< index 3)
                {:label (r/as-component [label])}))]
           [ui/card-content
            [ui/card-header (str "Event " (inc index))]
            [ui/card-description "This is an event."]]
           [ui/card-content {:extra true}
            [ui/icon {:name :clock}]
            "Starts in 10 days"]])
        (range 9))]]))

(defmethod render-view :about []
  (fn []
    [ui/header {:as :h1} "About"]))

(defn render []
  (let [view @(rf/subscribe [::route-subs/active-view])]
    [:<>
     [ui/menu
      {:fixed :top}
      [ui/container
       [ui/menu-item
        {:as     :a
         :header true
         :href   (routes/url-for ::routes/home)}
        [ui/image
         {:src  "/img/miles_logo_red_rgb.png"
          :size :tiny}]]
       [ui/menu-item
        {:as   :a
         :href (routes/url-for ::routes/about)}
        "About"]]]
     [ui/container
      {:text  true
       :style {:margin-top "7em"}}
      [(render-view view)]]]))

