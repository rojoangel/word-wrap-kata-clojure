(ns word.core-test
  (:use midje.sweet)
  (:use [word.core]))

(facts "about wrap"
       (fact "wraps empty as empty"
             (wrap nil 10) => ""
             (wrap "" 10) => "")
       (fact "wraps word as word"
             (wrap "word" 10) => "word")
       (fact "wraps a long word by splitting it"
             (wrap "longword" 4) => "long\nword"))
