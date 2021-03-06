;;;----------------------------------------------------------------------------------
;;; Generated by protoc-gen-clojure.  DO NOT EDIT
;;;
;;; Message Implementation of package com.example.addressbook
;;;----------------------------------------------------------------------------------
(ns com.example.addressbook
  (:require [protojure.protobuf :as pb]
            [protojure.protobuf.serdes :refer :all]
            [clojure.set :as set]
            [clojure.spec.alpha :as s])
  (:import (com.google.protobuf
            CodedInputStream)))

;;----------------------------------------------------------------------------------
;;----------------------------------------------------------------------------------
;; Forward declarations
;;----------------------------------------------------------------------------------
;;----------------------------------------------------------------------------------

(declare cis->Person)
(declare ecis->Person)
(declare new-Person)
(declare cis->Person-PhoneNumber)
(declare ecis->Person-PhoneNumber)
(declare new-Person-PhoneNumber)
(declare cis->AddressBook)
(declare ecis->AddressBook)
(declare new-AddressBook)
(declare cis->HelloResponse)
(declare ecis->HelloResponse)
(declare new-HelloResponse)

;;----------------------------------------------------------------------------------
;;----------------------------------------------------------------------------------
;; Enumerations
;;----------------------------------------------------------------------------------
;;----------------------------------------------------------------------------------

;-----------------------------------------------------------------------------
; Person-PhoneType
;-----------------------------------------------------------------------------
(def Person-PhoneType-val2label {
  0 :MOBILE
  1 :HOME
  2 :WORK})

(def Person-PhoneType-label2val (set/map-invert Person-PhoneType-val2label))

(defn cis->Person-PhoneType [is]
  (let [val (.readEnum is)]
    (get Person-PhoneType-val2label val val)))

(defn- get-Person-PhoneType [value]
  {:pre [(or (int? value) (contains? Person-PhoneType-label2val value))]}
  (get Person-PhoneType-label2val value value))

(defn size-Person-PhoneType [tag options value]
  (size-Enum tag options (get-Person-PhoneType value)))

(defn write-Person-PhoneType [tag options value os]
  (write-Enum tag options (get-Person-PhoneType value) os))



;;----------------------------------------------------------------------------------
;;----------------------------------------------------------------------------------
;; Message Implementations
;;----------------------------------------------------------------------------------
;;----------------------------------------------------------------------------------

;-----------------------------------------------------------------------------
; Person
;-----------------------------------------------------------------------------
(defrecord Person [name id email phones]
  pb/Writer

  (serialize [this os]
    (write-String 1  {:optimize true} (:name this) os)
    (write-Int32 2  {:optimize true} (:id this) os)
    (write-String 3  {:optimize true} (:email this) os)
    (write-repeated write-embedded 4 (:phones this) os))

  (length [this]
    (reduce + [(size-String 1  {:optimize true} (:name this))
(size-Int32 2  {:optimize true} (:id this))
(size-String 3  {:optimize true} (:email this))
(size-repeated size-embedded 4 (:phones this))])))

(s/def :com.example.addressbook.messages.Person/name string?)
(s/def :com.example.addressbook.messages.Person/id int?)
(s/def :com.example.addressbook.messages.Person/email string?)

(s/def ::Person-spec (s/keys :opt-un [:com.example.addressbook.messages.Person/name :com.example.addressbook.messages.Person/id :com.example.addressbook.messages.Person/email ]))
(def Person-defaults {:name "" :id 0 :email "" :phones [] })

(defn cis->Person
  "CodedInputStream to Person"
  [is]
  (->> (tag-map Person-defaults
         (fn [tag index]
             (case index
               1 [:name (cis->String is)]
               2 [:id (cis->Int32 is)]
               3 [:email (cis->String is)]
               4 [:phones (cis->repeated ecis->Person-PhoneNumber is)]

               [index (cis->undefined tag is)]))
         is)
        (map->Person)))

(defn ecis->Person
  "Embedded CodedInputStream to Person"
  [is]
  (cis->embedded cis->Person is))

(defn new-Person
  "Creates a new instance from a map, similar to map->Person except that
  it properly accounts for nested messages, when applicable.
  "
  [init]
  {:pre [(if (s/valid? ::Person-spec init) true (throw (ex-info "Invalid input" (s/explain-data ::Person-spec init))))]}
  (-> (merge Person-defaults init)
      (cond-> (contains? init :phones) (update :phones #(map new-Person-PhoneNumber %)))
      (map->Person)))

(defn pb->Person
  "Protobuf to Person"
  [input]
  (-> input
      CodedInputStream/newInstance
      cis->Person))

;-----------------------------------------------------------------------------
; Person-PhoneNumber
;-----------------------------------------------------------------------------
(defrecord Person-PhoneNumber [number type]
  pb/Writer

  (serialize [this os]
    (write-String 1  {:optimize true} (:number this) os)
    (write-Person-PhoneType 2  {:optimize true} (:type this) os))

  (length [this]
    (reduce + [(size-String 1  {:optimize true} (:number this))
(size-Person-PhoneType 2  {:optimize true} (:type this))])))

(s/def :com.example.addressbook.messages.Person-PhoneNumber/number string?)
(s/def :com.example.addressbook.messages.Person-PhoneNumber/type (s/or :keyword keyword? :int int?))
(s/def ::Person-PhoneNumber-spec (s/keys :opt-un [:com.example.addressbook.messages.Person-PhoneNumber/number :com.example.addressbook.messages.Person-PhoneNumber/type ]))
(def Person-PhoneNumber-defaults {:number "" :type (Person-PhoneType-val2label 0) })

(defn cis->Person-PhoneNumber
  "CodedInputStream to Person-PhoneNumber"
  [is]
  (->> (tag-map Person-PhoneNumber-defaults
         (fn [tag index]
             (case index
               1 [:number (cis->String is)]
               2 [:type (cis->Person-PhoneType is)]

               [index (cis->undefined tag is)]))
         is)
        (map->Person-PhoneNumber)))

(defn ecis->Person-PhoneNumber
  "Embedded CodedInputStream to Person-PhoneNumber"
  [is]
  (cis->embedded cis->Person-PhoneNumber is))

(defn new-Person-PhoneNumber
  "Creates a new instance from a map, similar to map->Person-PhoneNumber except that
  it properly accounts for nested messages, when applicable.
  "
  [init]
  {:pre [(if (s/valid? ::Person-PhoneNumber-spec init) true (throw (ex-info "Invalid input" (s/explain-data ::Person-PhoneNumber-spec init))))]}
  (-> (merge Person-PhoneNumber-defaults init)
      (map->Person-PhoneNumber)))

(defn pb->Person-PhoneNumber
  "Protobuf to Person-PhoneNumber"
  [input]
  (-> input
      CodedInputStream/newInstance
      cis->Person-PhoneNumber))

;-----------------------------------------------------------------------------
; AddressBook
;-----------------------------------------------------------------------------
(defrecord AddressBook [people]
  pb/Writer

  (serialize [this os]
    (write-repeated write-embedded 1 (:people this) os))

  (length [this]
    (reduce + [(size-repeated size-embedded 1 (:people this))])))

(s/def ::AddressBook-spec (s/keys :opt-un []))
(def AddressBook-defaults {:people [] })

(defn cis->AddressBook
  "CodedInputStream to AddressBook"
  [is]
  (->> (tag-map AddressBook-defaults
         (fn [tag index]
             (case index
               1 [:people (cis->repeated ecis->Person is)]

               [index (cis->undefined tag is)]))
         is)
        (map->AddressBook)))

(defn ecis->AddressBook
  "Embedded CodedInputStream to AddressBook"
  [is]
  (cis->embedded cis->AddressBook is))

(defn new-AddressBook
  "Creates a new instance from a map, similar to map->AddressBook except that
  it properly accounts for nested messages, when applicable.
  "
  [init]
  {:pre [(if (s/valid? ::AddressBook-spec init) true (throw (ex-info "Invalid input" (s/explain-data ::AddressBook-spec init))))]}
  (-> (merge AddressBook-defaults init)
      (cond-> (contains? init :people) (update :people #(map new-Person %)))
      (map->AddressBook)))

(defn pb->AddressBook
  "Protobuf to AddressBook"
  [input]
  (-> input
      CodedInputStream/newInstance
      cis->AddressBook))

;-----------------------------------------------------------------------------
; HelloResponse
;-----------------------------------------------------------------------------
(defrecord HelloResponse [message]
  pb/Writer

  (serialize [this os]
    (write-String 1  {:optimize true} (:message this) os))

  (length [this]
    (reduce + [(size-String 1  {:optimize true} (:message this))])))

(s/def :com.example.addressbook.messages.HelloResponse/message string?)
(s/def ::HelloResponse-spec (s/keys :opt-un [:com.example.addressbook.messages.HelloResponse/message ]))
(def HelloResponse-defaults {:message "" })

(defn cis->HelloResponse
  "CodedInputStream to HelloResponse"
  [is]
  (->> (tag-map HelloResponse-defaults
         (fn [tag index]
             (case index
               1 [:message (cis->String is)]

               [index (cis->undefined tag is)]))
         is)
        (map->HelloResponse)))

(defn ecis->HelloResponse
  "Embedded CodedInputStream to HelloResponse"
  [is]
  (cis->embedded cis->HelloResponse is))

(defn new-HelloResponse
  "Creates a new instance from a map, similar to map->HelloResponse except that
  it properly accounts for nested messages, when applicable.
  "
  [init]
  {:pre [(if (s/valid? ::HelloResponse-spec init) true (throw (ex-info "Invalid input" (s/explain-data ::HelloResponse-spec init))))]}
  (-> (merge HelloResponse-defaults init)
      (map->HelloResponse)))

(defn pb->HelloResponse
  "Protobuf to HelloResponse"
  [input]
  (-> input
      CodedInputStream/newInstance
      cis->HelloResponse))

