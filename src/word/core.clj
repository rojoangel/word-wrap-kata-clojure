(ns word.core
  (:require [clojure.string :as string]))

(defn- fits-in? [phrase columns]
  (<= (count phrase) columns))

(defn- last-whitespace-in [phrase columns]
  (string/last-index-of phrase " " columns))

(defn wrap [phrase columns]
  (loop [phrase phrase
         columns columns
         wrapped-phrase nil]
    (let [break-between
          (fn [phrase start end]
            [(string/join (concat (subs phrase 0 start) "\n"))
             (subs phrase end)])]
      (if (fits-in? phrase columns)
        (string/join (concat wrapped-phrase phrase))
        (if-let [whitespace-pos (last-whitespace-in phrase columns)]
          (recur
            (second (break-between phrase whitespace-pos (inc whitespace-pos)))
            columns
            (string/join (concat wrapped-phrase (first (break-between phrase whitespace-pos (inc whitespace-pos))))))
          (recur
            (second (break-between phrase columns columns))
            columns
            (string/join (concat wrapped-phrase (first (break-between phrase columns columns))))))))))
