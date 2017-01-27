(ns word.core
  (:require [clojure.string :as string]))

(defn wrap [phrase columns]
  (let [break-between
        (fn [phrase start end]
          (string/join
            (cons (subs phrase 0 start)
                  (cons "\n"
                        (wrap (subs phrase end) columns)))))]
    (if (<= (count phrase) columns)
      phrase
      (if-let [whitespace-pos (string/last-index-of phrase " " columns)]
        (break-between phrase whitespace-pos (inc whitespace-pos))
        (break-between phrase columns columns)))))
