(ns advent-of-code.day1)

(def input
  (-> "resources/input/day1"
      slurp
      (.split "\\n")
      vec))

(defn count-increases
  [ipt]
  (reduce (fn [{:keys [last-element increase-count]} el]
            (let [as-int (Integer/parseInt el)]
              {:last-element as-int,
               :increase-count (if (and last-element (> as-int last-element))
                                 (inc increase-count)
                                 increase-count)}))
    {:last-element nil, :increase-count 0}
    ipt))

(count-increases)
(:increase-count (count-increases input))

(defn zip-with-self-offset-1 [ipt] (map vector ipt (concat [nil] ipt)))


(def test-ipt ["1" "2" "3" "0"])
(defn count-increases-zipped
  [ipt]
  (let [ints (map #(Integer/parseInt %) ipt)
        zipped (zip-with-self-offset-1 ints)
        increases (map (fn [[fst snd]]
                         (if (and snd (> fst snd)) "increase" "not-increase"))
                    zipped)]
    (count (filter (partial = "increase") increases))))
