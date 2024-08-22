(ns com.iraqidata.tasks.day-2
  (:require
   [clojure.string :as str]
   scicloj.clay.v2.api
   [tablecloth.api :as tc]
   [tablecloth.column.api :as tcc]
   tech.v3.datatype.casting))

;; Namespace does not match the file.  You did not checkout the proper branch
;; and thus do not have the data to load, thus nothing evaluates.

(def ds (tc/dataset "./resources/data/flights.csv" {:key-fn #(keyword (str/replace (name %) "_" "-"))}))

;; 1. How many flights were there in total?

(tc/row-count ds)




;; 2. How many unique carriers were there in total?

(-> ds
    (tc/unique-by :carrier)
                  (tc/row-count))



;; 3. How many unique airports were there in total?


(+
(-> ds
    (tc/unique-by :origin)
    (tc/row-count))

(-> ds
    (tc/unique-by :dest)
    (tc/row-count)))




;; 4. What is the average arrival delay for each month?


(-> ds
    (tc/group-by :month)
    (tc/aggregate {:avg-arrival-delay (fn [x] (tc/mean x :arr-delay))}))



;; Optional: Use the `airlines` dataset to get the name of the carrier with the
;; highest average distance.


(def df (tc/dataset "./resources/data/airlines.csv" {:key-fn #(keyword (str/replace (name %) "_" "-"))}))

(-> df
    (tc/inner-join ds :carrier)
    (tc/group-by :name)
    (tc/aggregate {:avg-distance (fn [x] (tc/mean x :distance))})
    (tc/order-by :avg-distance-summary :desc)
    (tc/first))
