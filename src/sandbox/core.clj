(ns sandbox.core
  (:require [clojure.test :refer [with-test is deftest]]))


<<<<<<< HEAD
(with-test (defn my-freq [col]
             (reduce (fn [res x]
                       (let [y (get res x 0)]
                         (assoc res x (inc y))))
                     {}
                     col))
           (is (= (my-freq [1 2 1 3 5 5 5]) {1 2 2 1 3 1 5 3}))
           (is (= (my-freq []) {}))
           (is (= (my-freq [:a 1 :b 2 :a]) {:a 2 :b 1 2 1 1 1})))



(defn comp-2 [f g]
  (fn [x]
    (f (g x))))

((comp-2 list inc) 1)

(defn partial-2 [f p]
  (fn [x]
    (f p x)))

((partial-2 str "hello ") "Tal   ")


(defn f2 [x]
  (loop [x x
         res 1]
    (if (= 1 x)
      res
      (recur (dec x) (* res x)))))

(with-test (defn f3 [col]
             (loop [col col
                    res 0]
               (if (empty? col)
                 res
                 (recur (rest col) (+ res 1)))))
           (is (= [1 2 3 4 5]) 5))


(with-test (defn find-in-list [lst x]
             (loop [lst lst]
               (cond
                 (empty? lst) false
                 (= (first lst) x) true
                 :else (recur (rest lst)))))
           (is (= (find-in-list '(1 2 3 "a" "b") 3) true)))


(with-test (defn count-multi
             ([] 0)
             ([& a] (+ 1 (apply count-multi (rest a))))
             )
           (is (= (count-multi 1 2 3 4) 4)))

(defprotocol IAnimal
  (my-name [this])
  (my-age [this n]))

(deftype Dog []
  IAnimal
  (my-name [this] "dog")
  (my-age [this n] (repeat n "dog")))

(my-age (Dog.) 5)

(reduce
  (fn [res x]
    (* res x))
  1
  (range 1 4)
  )

=======
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
>>>>>>> 5f0c474142af18aa9720d240c6d645a216e776fc
