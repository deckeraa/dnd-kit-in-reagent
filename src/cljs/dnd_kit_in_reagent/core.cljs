(ns dnd-kit-in-reagent.core
  (:require
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]))

(defn app []
  [:div {} "app goes here"])

(defn mount-root []
  (rdom/render [app] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
