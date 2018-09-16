(ns sandbox.core
  (:require [clojure.test :refer [with-test is deftest]]))


;;Functions - Practice
(defn hello "receives a name and returns a string with the concatenation of \"Hello\" and the name"
  [name]
  (str "Hello " name))

(defn zero-check "receives a number and returns \"zero\" if the number is zero and \"non-zero\" otherwise"
  [n]
  (if (zero? n) "zero" "non-zero"))
(assert (= (zero-check 0) "zero"))
(assert (= (zero-check 1) "non-zero"))

(defn applicator [func number]
  (func number))
(assert (= (applicator inc 19) 20))



;;Lists - Practice
(defn fifth [lst]
  (first (rest
           (rest
             (rest
               (rest lst))))))
(assert (= (fifth '(1 2 3 4 5 6 7 8)) 5))


(defn nth-element [lst n]
  (if (zero? (dec n))
    (first lst)
    (nth-element (rest lst) (dec n))))
(assert (= (nth-element '(1 2 3 4 5 6 7 8 9 10 11) 10) 10))

(defn select-1-5-7 [lst]
  (list (nth-element lst 1) (nth-element lst 5) (nth-element lst 7)))
(assert (= (select-1-5-7 (range 10)) '(0 4 6)))


;;Vectors - Practice
(defn ->list [vec]
  (if (empty? vec)
    ()
    (cons (first vec) (->List (rest vec)))))
(assert (= (->list [1 2]) '(1 2)))


;;Maps - Practice
(defn map-object [f m]
  (zipmap (keys m) (map f (vals m))))
(map-object #(* 100 %) {:a 1 :b 2 :c 3})
(assert (= (map-object #(* 100 %) {:a 1 :b 2 :c 3}) {:a 100 :b 200 :c 300}))

(defn sequence->map [s]
  (zipmap (range) s))
(assert (= (sequence->map [10 20 30]) {0 10 1 20 2 30}))


(defn submap [m1 m2]
  (if (empty? m1)
    true
    (if (get m2 (first (keys m1)))
      (submap (rest m1) m2)
      false)))
(submap {:a 1 :b 2} {:a 1 :b 2 :c 3})
(assert (true? (submap {:a 1 :b 2} {:a 1 :b 2 :c 3})))
(assert (false? (submap {:a 1 :b 2 :c nil} {:a 1 :b 2})))
