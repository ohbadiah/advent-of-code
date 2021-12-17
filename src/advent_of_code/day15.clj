(ns advent-of-code.day15)

(defn num-string-to-num-vec [num-string]
  (->> num-string
     seq
     (map (comp #(Integer/parseInt %) str))))

(def input  (let [list-of-strs  (-> "resources/input/day15"
                                    slurp
                                    (.split "\\n")
                                    vec)]
              (->> list-of-strs
                   (map num-string-to-num-vec)
                   to-array-2d)))

(def start-pos [0 0])
(def end-pos [99 99])

(defn move-right [[x y]]
  [(inc x) y])

(defn move-down [[x y]]
  [x (inc y)])

(defn get-at-coordinate [ipt [x y]]
  (try
    (aget ipt x y)
    (catch java.lang.ArrayIndexOutOfBoundsException e
      10)))

#_(get-at-coordinate input [1000 0])


(defn run-sim [ipt] (loop [pos start-pos
                        cost-of-trip 0]
                   (let [look-right (get-at-coordinate ipt (move-right pos))
                         look-down (get-at-coordinate ipt (move-down pos))]
                     (cond
                       (= pos end-pos)
                       (do
                         (println "We reached the end!")
                         cost-of-trip)

                       (< look-right look-down)
                       (do
                         (println (format "Moving right from %s" pos))
                         (recur (move-right pos) (+ cost-of-trip look-right)))

                       :else
                       (do
                         (println (format "Moving down from %s" pos))
                         (recur (move-down pos) (+ cost-of-trip look-down)))))))
#_(run-sim input)
