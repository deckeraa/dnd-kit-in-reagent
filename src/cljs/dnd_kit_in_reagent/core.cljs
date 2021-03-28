(ns dnd-kit-in-reagent.core
  (:require
   [reagent.core :as r]
   [reagent.dom :as rdom]
   ["@dnd-kit/core" :as dnd-kit :refer [DndContext useDraggable useDroppable]]
   ["@dnd-kit/utilities" :refer [CSS]]))

(defn droppable []
  (let [hook-ret (useDroppable (clj->js {:id "droppable"}))]
    (r/as-element
     [:div {:ref (.-setNodeRef hook-ret)} "Droppable element"])))

(defn draggable []
  (let [hook-ret (useDraggable (clj->js {:id "draggable"}))]
    (r/as-element
     [:div {:ref (.-setNodeRef hook-ret)} (str "Draggable element: " (.-transform hook-ret) (js->clj hook-ret))])))

(defn app []
  [:> DndContext {:onDragStart (fn [e] (println "onDragStart: " e))}
   [:> droppable]
   [:> draggable]
   ])

(defn mount-root []
  (rdom/render [app] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
