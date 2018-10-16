(ns miles-events.material-ui.icons
  (:refer-clojure :exclude [list loop map print remove repeat shuffle sort update filter compare comment])
  (:require-macros
    [miles-events.material-ui.macros :refer [generate-mui-icon-fns]])
  (:require
    [reagent.core :as r]))

(generate-mui-icon-fns)