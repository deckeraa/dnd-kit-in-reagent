(ns dnd-kit-in-reagent.core
  (:require
   [reagent.core :as r]
   [reagent.dom :as rdom]
   ["@dnd-kit/core" :as dnd-kit :refer [DndContext useDraggable useDroppable]]
   ["@dnd-kit/utilities" :refer [CSS]]))

(defn droppable []
  (let [{:keys [isOver setNodeRef]} (js->clj (useDroppable (clj->js {:id "droppable"})))]
    (r/as-element
     [:div {:ref setNodeRef} "Droppable element"])))

(defn draggable []
  (let [{:keys [attributes listeners setNodeRef transform]} (js->clj (useDraggable (clj->js {:id "draggable"})))
        style {:transform (.toString (.-Translate CSS) transform)}]
    (r/as-element
     [:div {:ref setNodeRef :style style} (str "Draggable element: " style)])))

(defn app []
  [:> DndContext {:onDragStart (fn [e] (println "onDragStart: " e))}
   [:> droppable]
   [:> draggable]
   ])

(defn mount-root []
  (rdom/render [app] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
