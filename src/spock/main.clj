(ns spock.main
  (:gen-class)
  (:require 
    [clojure.core.async :as a :refer [<! >! <!! >!! chan put! take! go alt! alts! do-alts close! timeout pipe mult tap untap 
                                      pub sub unsub mix admix unmix dropping-buffer sliding-buffer pipeline pipeline-async to-chan! thread]]
    [clojure.string]
    [clojure.java.io :as io])
  (:import
    (java.io File)
    (javax.swing JFrame WindowConstants ImageIcon JTree JPanel JScrollPane BoxLayout JEditorPane)
    (javax.swing.tree DefaultTreeModel DefaultMutableTreeNode)
  )    
)

#_(println (System/getProperty "clojure.core.async.pool-size"))
(do (set! *warn-on-reflection* true) (set! *unchecked-math* true))

(defonce stateA (atom nil))
(def ^:dynamic jframe nil)

(defn window
  []
  (let [jframe (JFrame. "i am spock program")
        files (->>
                (io/file (System/getProperty "user.dir"))
                (.listFiles)
              )
        jpanel (JPanel.)
        scroll-pane (JScrollPane.)
        root (DefaultMutableTreeNode. "root")
        tree-model (DefaultTreeModel. root)
        jtree (JTree. tree-model)
        editor-pane (JEditorPane.)
        layout (BoxLayout. jpanel BoxLayout/X_AXIS)
        ]

  (when-let [url (io/resource "icon.png")]
    (.setIconImage jframe (.getImage (ImageIcon. url)))
  )

  (doseq [^File file files]
    (println (.getName file))
    (.add root (DefaultMutableTreeNode. (.getName file)))
  )
  (.reload tree-model)

  (doto jtree
    #_(.setRootVisible​ true)
    (.setSize 420 1440)
  )

  (doto scroll-pane
    (.setViewportView jtree)
  )

  (doto editor-pane
    (.setSize 1500 1440)
  )

  (doto jpanel
    (.setLayout layout)
    (.add scroll-pane)
    (.add editor-pane)
  )

  (doto jframe
    (.setDefaultCloseOperation WindowConstants/DISPOSE_ON_CLOSE)
    (.setSize 1920 1440)
    (.setLocation 1700 200)
    (.add jpanel)
    (.setVisible true)
  )

  (alter-var-root #'spock.main/jframe (constantly jframe))

  nil
  )
)

(defn reload
  []
  (require 
    '[spock.main]
    :reload)
)

(defn -main
  [& args]
  (let []
    (reset! stateA {})

    (window)

  )
)
