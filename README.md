# 🚪 Gate Control System — Event-Driven Architecture Demo
## Overview

This project is a conceptual and technical demonstration of how to design a small but realistic system with clear separation of responsibilities, event-driven flow, and explicit decision boundaries.

The goal of this project is not to showcase frameworks, UI, or external integrations, but to demonstrate system thinking:  
how events flow, how decisions are centralized, how behavior is isolated, and how the system remains maintainable under change.  

Although the domain is simple (a gate), the architecture mirrors patterns used in real-world systems that deal with concurrency, safety, and external signals.  

## Why this project exists
Many beginner projects focus on making code work.
This project focuses on making decisions explicit.

The main objective is to demonstrate that I can:

- reason about system behavior, not only syntax
- separate inputs, policies, and execution
- design code that is easy to read, extend, and maintain
- think about how real-world events interact with software
- build software that is observable, not opaque

In short:  
  👉 _This project shows that I don’t just write code — I design systems._

## System perspective (high-level)

From a system point of view, the gate behaves like a reactive machine:

External World  
  ↓  
Events  
  ↓  
Dispatcher (policy & priority)  
  ↓  
Gate (state holder & executor)  
  ↓  
State behavior  

The system reacts to events, not direct method calls with logic embedded.  
This design ensures that:

- safety rules are centralized

- behavior is predictable

- future integrations (hardware, UI, network) remain simple

## Core concepts
### 1. Events as first-class objects

Every interaction with the system is modeled as an Event:  

- user commands (open, close, stop)
- sensor signals (obstacle detected)
- critical conditions (hazard, lock)

Events:

- carry intent
- declare priority
- do not contain business logic

This avoids conditionals scattered throughout the system and makes the flow explicit.

### 2. Dispatcher — the policy layer

The Dispatcher is the single point of decision.  
Its responsibilities:

- receive events
- evaluate priority and constraints
- decide whether an event is accepted or blocked
- forward accepted events to the gate

The dispatcher does not:

- change state
- execute behavior
- contain domain logic

This separation ensures that policy does not leak into behavior.

### 3. Gate — orchestration, not policy

The Gate represents the system’s internal model:

- current state
- internal flags (locked, obstacle, hazard)
- allowed transitions

The gate:

- executes approved actions
- delegates behavior to states
- never evaluates priority or global rules

This makes the gate predictable and easy to reason about.

### 4. States — isolated behavior

Each state represents how the gate behaves, not when it is allowed to act.  
States:

- handle open / close / stop requests
- decide valid transitions
- remain unaware of global policies or priorities

This avoids defensive programming and keeps states simple.

### 5. Observability through structured logs

Instead of scattered println statements, the system uses a centralized logger that narrates execution:  

- Event received
- Dispatch decision (accepted / blocked)
- State transition
- Action started
- Action denied (when applicable)

This allows the system to be understood by running it, without debugging or inspecting internal variables.

## Why not a simpler solution?

Yes — the entire logic could have been placed in a single Gate class with conditionals.  
That approach was intentionally avoided.

This project prioritizes:

- maintainability over minimal code
- clarity over shortcuts
- explicit decisions over implicit behavior

The architecture scales conceptually even if the example is small.

## Real-world applicability

This design can be directly adapted to:

- physical gate controllers
- embedded systems
- event-driven services
- hardware integrations (sensors, relays)
- simulations and safety-critical workflows

Only the event sources and outputs would change — the core logic remains valid.

## Trade-offs and conscious decisions

- The dispatcher is synchronous for simplicity
- Logging is centralized and static for demonstration purposes
- No external frameworks are used intentionally
- Safety rules are modeled logically, not physically (hardware fail-safes are assumed)

These choices were made to keep the focus on architecture, not tooling.

## Run the simulation

``` bash
git clone https://github.com/Vouxer09/event-driven-gate-controller
cd event-driven-gate-controller
mvn clean compile
mvn exec:java
```
## Final note

This project represents how I approach software:

- start from the system, not the syntax
- make decisions explicit
- design for the next developer
- treat code as something that will be read, not only executed

#### If you are reviewing this repository, I invite you to:

- read the logs
- follow the event flow
- observe how responsibilities are separated

That is the real demonstration.
