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
             (wrap "longword" 4) => "long\nword"
             (wrap "longerword" 6) => "longer\nword"
             (wrap "verylongword" 4) => "very\nlong\nword")
       (fact "wraps multiple words by using whitespaces"
             (wrap "word word" 6) => "word\nword"
             (wrap "wrap here" 6) => "wrap\nhere"
             (wrap "word word word" 6) => "word\nword\nword"))

