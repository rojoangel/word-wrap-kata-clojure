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

(defn- split [phrase columns]
  (if-let [whitespace-pos (last-whitespace-in phrase columns)]
    (break-between phrase whitespace-pos (inc whitespace-pos))
    (break-between phrase columns columns)))

(defn wrap [phrase columns]
  (loop [wrapped-phrase nil
         phrase phrase
         columns columns]
    (if (fits-in? phrase columns)
      (string/join (concat wrapped-phrase phrase))
      (let [splitted-phrase (split phrase columns)]
        (recur (concat wrapped-phrase (first splitted-phrase))
               (second splitted-phrase)
               columns)))))
