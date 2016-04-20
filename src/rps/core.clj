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
  (update-player [this,_,_] this ) )

(defrecord StubbornPlayer [choice]
  Player
  (choose [_] (choice))
  (update-player [this,_,_] this) )

(defrecord MeanPlayer [last-winner]
  Player
  (choose [_]
    (if last-winner last-winner (rand-nth choices)))
  (update-player [_,p1-choice,p2-choice]
    (->MeanPlayer (when (iwon? p1-choice p2-choice) p1-choice))) )

(defn game
  "Play Rock Paper Scissors!"
  [p1, p2, rounds]
  ;; loop with 5 variables
  ;; p1, p2, p1-curr-score, p2-curr-score, rounds-remaining
  
  )

(defn -main
  "Rock Paper Scissors - interactive game!"
  [& args]
  (println "Welcome to Rock, Paper, Scissors - interactive game!"))
