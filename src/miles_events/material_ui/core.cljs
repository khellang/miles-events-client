(ns miles-events.material-ui.core
  (:refer-clojure :exclude [list])
  (:require-macros
    [miles-events.material-ui.macros :refer [generate-mui-fns]])
  (:require
    [camel-snake-kebab.core :refer [->camelCaseString ->kebab-case-keyword]]
    [camel-snake-kebab.extras :refer [transform-keys]]
    [reagent.core :as r]))

(defn create-mui-theme
  ([] (create-mui-theme nil))
  ([theme] (->> theme
             (transform-keys ->camelCaseString)
             (clj->js)
             (.createMuiTheme js/MaterialUI))))

(defn- wrap-styles [styles]
  (fn [theme]
    (->> (styles theme)
      (transform-keys ->camelCaseString)
      (clj->js))))

(defn- wrap-component [component]
  (fn [props]
    (->> props
      (js->clj)
      (transform-keys ->kebab-case-keyword)
      (component))))

(defn with-styles [styles component]
  (let [factory
        (-> styles
          wrap-styles
          js/MaterialUI.withStyles)]
    (-> component
      wrap-component
      r/reactify-component
      factory
      r/adapt-react-class)))

(defn color [hue shade]
  (aget
    js/MaterialUI
    "colors"
    (->camelCaseString hue)
    (str shade)))

(generate-mui-fns)
