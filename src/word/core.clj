(ns word.core
  (:require [clojure.string :as string]))

(defn- fits-in? [phrase columns]
  (<= (count phrase) columns))

(defn- last-whitespace-in [phrase columns]
  (string/last-index-of phrase " " columns))

(defn wrap [phrase columns]
  (let [break-between
        (fn [phrase start end]
          (string/join
            (cons (subs phrase 0 start)
                  (cons "\n"
                        (wrap (subs phrase end) columns)))))]
    (if (fits-in? phrase columns)
      phrase
      (if-let [whitespace-pos (last-whitespace-in phrase columns)]
        (break-between phrase whitespace-pos (inc whitespace-pos))
        (break-between phrase columns columns)))))
