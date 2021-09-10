CREATE DATABSE Cassandra;
USE Cassandra;
CREATE TABLE Daily
(
    Open           decimal(10, 4) NOT NULL,
    High           decimal(10, 4) NOT NULL,
    Low            decimal(10, 4) NOT NULL,
    Close          decimal(10, 4) NOT NULL,
    Volume         bigint         NOT NULL,

    AdjustedOpen   decimal(10, 4),
    AdjustedHigh   decimal(10, 4),
    AdjustedLow    decimal(10, 4),
    AdjustedClose  decimal(10, 4),
    AdjustedVolume bigint,

    SplitFactor    decimal(6,3),

    Symbol         varchar(10),
    Exchange       varchar(5),
    Date           TIMESTAMP,
    CONSTRAINT Pk_Daily PRIMARY KEY (Symbol, Exchange, Date)
);
