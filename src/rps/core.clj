(ns rps.core
  (:gen-class))

;; Both a data structure and a function to define the rules of the game
;; Can call with (dominates :paper) => :rock
;;
(def dominates
  {:rock     :scissors
   :paper    :rock
   :scissors :paper})

;; Extract the choices one can make for ease of access
;;
(def choices (vec (keys dominates)))

(defn winner
  "Take two players' choices and return the winner (or nil for tie)"
  [p1 p2]
  (cond
    (= (dominates p2) p1) p2
    (= (dominates p1) p2) p1
    :else nil))

(defn draw? [p1 p2] (= p1 p2))
(defn iwon? [p1 p2] (= p1 (winner p1 p2)))

(defprotocol Player
  "Methods for a player of Rock, Paper, Scissors"
  (choose [p] "Takes a player, and returns player's choice")
  (update-player [player, p1-choice, p2-choice] "Takes player, his/her last choice, opponent's last choice, and returns new Player for next round"))

(defrecord RandomPlayer []
  Player
  (choose [_] (rand-nth choices))
  (update-player [_,_,_] this ) )

(defn -main
  "Rock Paper Scissors - interactive game!"
  [& args]
  (println "Welcome to Rock, Paper, Scissors - interactive game!"))
