
<div align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/5/50/Oracle_logo.svg" alt="Oracle Database" width="300"/>
  
  <br><br>

  <h1 style="font-family: 'Segoe UI', sans-serif; font-weight: 300; letter-spacing: 4px; text-transform: uppercase;">StringForge DBMS</h1>
  <h3 style="font-family: 'Segoe UI', sans-serif; font-weight: 400; color: #555;">Supply Chain & Manufacturing Data Core</h3>

  <p>
    <img src="https://img.shields.io/badge/Database-Oracle_19c-F80000?style=for-the-badge&logo=oracle&logoColor=white" alt="Oracle"/>
    <img src="https://img.shields.io/badge/Language-Java_JDBC-5382A1?style=for-the-badge&logo=java&logoColor=white" alt="Java"/>
    <img src="https://img.shields.io/badge/Design-Oracle_Data_Modeler-F80000?style=for-the-badge&logo=oracle&logoColor=white" alt="Data Modeler"/>
    <img src="https://img.shields.io/badge/Paradigm-Relational_Modeling_(3NF)-000?style=for-the-badge" alt="3NF"/>
  </p>

  <p align="center" style="max-width: 800px; margin: auto;">
    <strong>A strictly normalized database architecture engineered to digitize the manufacturing lifecycle of musical instrument strings. 
    From raw material procurement (spool tracking) to production work orders and B2B/B2C sales.</strong>
  </p>

  <p>
    <a href="specification.docx"><strong>📄 Domain Specification »</strong></a>
    ·
    <a href="EER diagram.png"><strong>🗺 View EER Diagram »</strong></a>
    ·
    <a href="DDL_datamodeler.ddl"><strong>💾 SQL DDL Script »</strong></a>
  </p>
</div>

<br>

---

## 1. Project Overview & Motivation

**StringForge** is a specialized **ERP (Enterprise Resource Planning)** module designed to optimize the operations of a family-owned instrument string manufactory. The primary objective was to replace legacy manual tracking systems with a robust, digital "Source of Truth."

The system addresses specific domain challenges:
*   **Traceability:** Tracking which specific material spools were used to create a batch of strings.
*   **Complex Hierarchy:** Handling diverse customer types (Individuals, Companies, Associations) using **IS-A inheritance modeling**.
*   **Recursive Logic:** Managing complex orders that may consist of smaller sub-orders.

---

## 2. Database Modeling & Design

The core value of this project lies in the rigorous **Data Modeling Process**, transforming abstract business requirements into a performant physical schema.

### 2.1 Conceptual Layer (EER)
We utilized **Oracle Data Modeler** to construct an Extended Entity-Relationship (EER) diagram. Key architectural decisions include:
*   **Bill of Materials (BOM):** Modeled the Many-to-Many relationship between `Material` and `String` (Product), allowing a single string to be composed of multiple alloys (e.g., copper winding on steel core).
*   **Inventory Management:** The `Spool` entity acts as a specific instance of `Material`, enabling granular stock tracking (capacity vs. usage).
*   **Production Workflow:** The `ProductionWorkOrder` (Nalog za izradu) connects Sales Orders (`Narudzbina`) to specific Articles, creating a clear link between demand and supply.

### 2.2 Logical & Physical Layer
The design was translated into a Relational Model adhering to **3rd Normal Form (3NF)** to ensure data integrity and minimize redundancy.
*   **Supertype/Subtype Implementation:** The `Customer` (Kupac) entity uses an exclusive-or strategy to handle `Individual`, `Company`, and `Association` subtypes, enforcing strict data validation rules.
*   **Recursive Relationships:** Implemented self-referencing foreign keys on the `Order` table to support nested order structures.

---

## 3. Technology Stack

*   **RDBMS:** Oracle Database 19c/21c
*   **Connectivity:** Java Database Connectivity (JDBC) - chosen for low-level control over SQL execution and batch processing.
*   **Modeling Tools:** Oracle SQL Developer Data Modeler.
*   **Query Language:** PL/SQL & ANSI SQL (Complex Joins, Aggregations, Views).

---

## 4. Key Entities and Domain Logic

The schema covers the entire manufacturing value chain:

| Entity / Module | Responsibility |
| :--- | :--- |
| **Production Order** | Tracks the lifecycle of a manufacturing job (Pending -> In Progress -> Completed). Links specific customer demands to factory output. |
| **Material & Spools** | Manages raw inventory. Differentiates between abstract material types (e.g., "Silver-plated Copper 0.5mm") and physical spools in the warehouse. |
| **Product (String)** | Defines the technical specifications of the output (Length, Winding material, Core material, Instrument type). |
| **Sales Order** | Handles complex ordering logic, including recursive sub-orders and status tracking (Sent, Production, Waiting). |
| **Stakeholders** | A polymorphic design handling B2B (Companies), B2C (Individuals), and NGOs (Cultural Associations). |

---

## 5. JDBC Implementation & Query Optimization

The `jdbc_project` folder contains a Java application acting as the interface layer. Instead of using an ORM (like Hibernate) which hides complexity, this project utilizes raw JDBC to demonstrate **SQL proficiency**.

### Key Features Implemented:
*   **Transactional Integrity:** Manual management of `Connection.commit()` and `Connection.rollback()` ensures atomic operations during multi-step production updates.
*   **Complex Analytical Queries:**
    *   *Inventory Forecasting:* Queries calculating remaining material based on active production orders.
    *   *Sales Analytics:* Aggregating revenue by Customer Type (Company vs Individual) over specific time windows.
    *   *Traceability:* Recursive queries identifying all raw materials used in a specific completed Order.

---

## 6. Repository Structure

This repository serves as a complete artifact of the database engineering lifecycle.

*   `data_modeler_project/` - Source files for the Oracle Data Modeler design.
*   `jdbc_project/` - Java source code for database connectivity and logic testing.
*   `DDL_datamodeler.ddl` - Auto-generated Data Definition Language script for schema creation.
*   `EER diagram.png` - Visual representation of the conceptual model.
*   `Manual_translation_...` - Documentation on the mapping strategy from Conceptual to Relational models.
*   `test_data.dml` - Script for populating the database with realistic mock data for stress testing.

---

## 7. Project Context

**Developer:** Teodora Bečejac
**Domain:** Manufacturing & Supply Chain

This system was conceptualized to solve real-world inefficiencies in a family-owned manufacturing business, moving from pen-and-paper tracking to a digital, queryable database system.
