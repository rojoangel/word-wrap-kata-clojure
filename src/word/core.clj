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

(defn- split-phrase [phrase columns]
  (if-let [whitespace-pos (last-whitespace-in phrase columns)]
    (break-between phrase whitespace-pos (inc whitespace-pos))
    (break-between phrase columns columns)))

(defn wrap [phrase columns]
  (loop [phrase phrase
         columns columns
         wrapped-phrase nil]
    (if (fits-in? phrase columns)
      (string/join (concat wrapped-phrase phrase))
      (if-let [whitespace-pos (last-whitespace-in phrase columns)]
        (let [splitted-phrase (break-between phrase whitespace-pos (inc whitespace-pos))]
          (recur (second splitted-phrase) columns (concat wrapped-phrase (first splitted-phrase))))
        (let [splitted-phrase (break-between phrase columns columns)]
          (recur (second splitted-phrase) columns (concat wrapped-phrase (first splitted-phrase))))))))
