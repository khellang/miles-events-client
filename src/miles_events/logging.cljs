(ns miles-events.logging
  (:require
    [taoensso.timbre :as timbre]
    [devtools.preload]))

(def devtools-level->fn
  {:fatal js/console.error
   :error js/console.error
   :warn  js/console.warn
   :info  js/console.info
   :debug js/console.debug
   :trace js/console.trace})

(def devtools-appender
  {:enabled?   true
   :async?     false
   :min-level  nil
   :rate-limit nil
   :output-fn  nil
   :fn         (fn [data]
                 (let [{:keys [level ?ns-str ?line vargs_]} data
                       vargs (list* (str ?ns-str ":" ?line) (force vargs_))
                       f (devtools-level->fn level js/console.log)]
                   (.apply f js/console (to-array vargs))))})

(defn initialize [{:keys [level]}]
  (timbre/merge-config! (merge {:level (keyword level)}
                          (when (= "Google Inc." js/navigator.vendor)
                            {:appenders {:console devtools-appender}}))))