(ns word.core
  (:require [clojure.string :as string]))

(defn- fits-in? [phrase columns]
  (<= (count phrase) columns))

(defn- last-whitespace-in [phrase columns]
  (string/last-index-of phrase " " columns))

(defn- break-between [phrase start end]
  (list
    (concat (subs phrase 0 start) "\n")
    (subs phrase end)))

(defn wrap [phrase columns]
  (loop [phrase phrase
         columns columns
         wrapped-phrase nil]
    (if (fits-in? phrase columns)
      (string/join (concat wrapped-phrase phrase))
      (if-let [whitespace-pos (last-whitespace-in phrase columns)]
        (recur
          (second (break-between phrase whitespace-pos (inc whitespace-pos)))
          columns
          (concat wrapped-phrase (first (break-between phrase whitespace-pos (inc whitespace-pos)))))
        (recur
          (second (break-between phrase columns columns))
          columns
          (concat wrapped-phrase (first (break-between phrase columns columns))))))))
