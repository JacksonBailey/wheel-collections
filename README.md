# Wheel Collections

Reinventing the wheel. This specific project focuses on collections.

- Goals:
  - Learn about API design
  - Make something us*able*
  - Make use of new Java features (e.g., `Optional`)
  - Mirror Java where appropriate
  - Don't duplicate names
- Non goals:
  - Making something use*ful*
  - Making implementations from scratch -- wrappers around existing things is fine
  - Good names -- Things like `Iterable2` are fine

## The standard collections API

For reference.

- Iterable -> Collection
  - Set -> SortedSet -> NavigableSet
  - List
  - Queue -> Deque
- Map (By itself)

## Contracts and methods

What is an interface? Is it the methods it defines? Or is it more?

They're not only the methods they define but the contract they describe.

When reinventing the wheel you may (like I did) assume you can make modifiable collections extend
unmodifiable ones. You may have even seen in an FAQ by Oracle that they didn't because it's too many
interfaces. Well, it is, but you may have assumed that it could still be in the same hierarchy.

The core of this is when an interface has conceptually more methods but cannot behave by the
contract of the interface with conceptually fewer methods. Modifiable collections have more methods
than unmodifiable ones but because they can be modified they cannot behave under the "unmodifiable"
part.

### The problem with modifiable hierarchy

A quick illustration of why modifiable and unmodifiable cannot be part of the interface hierarchy
for collections. You have to accept one of the corners because either modifiable extends
unmodifiable (the naive approach because there are more methods) or unmodifiable extends modifiable.
Effectively Java uses that second approach. You can call `Collections.unmodifiable...` to make
something unmodifiable. You'll get errors if you try to modify it.

|            | Expect mod | Expect unmod    |
|------------|------------|-----------------|
| Give mod   | Okay       | Not thread safe |
| Give unmod | Errors     | Okay            |

### The problem with capacity-restricted hierarchy

Counter intuitively you can have uncapped extend capped. A simple way to understand this is checking
if an infinitely large container is full before you put something in it. Was it a waste of time?
Technically, sure, but you didn't break anything.

|               | Expect capped  | Expect uncapped |
|---------------|----------------|-----------------|
| Give capped   | Okay           | Errors          |
| Give uncapped | Actually okay! | Okay            |
