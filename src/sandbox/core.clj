(ns sandbox.core
  (:require [clojure.test :refer [with-test is deftest]]))


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

