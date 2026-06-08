# 📋 Lab Manual: Intelligent City Search (TDD)

The specification below contains 10 steps for developing the `CitiesSearch` system. Following the TDD methodology, for each step you should first create a test case in your test class (**RED** phase), then implement the minimum logic required to pass the test (**GREEN** phase), and optionally clean up the code (**REFACTOR** phase).

**Available cities (in-memory database):**
*Warszawa, Wrocław, Kraków, Gdynia, Nowy Jork, Los Angeles, San Francisco, Zakopane, Ankara, Santiago*

---

### Step 1: Substring match
* **Description:** The search engine should return cities that contain the input query anywhere within their name.
* **Acceptance Criteria:** For the query `"Fran"`, the system must return a list containing `"San Francisco"`.
* **Implementation Tips:** Filter the stream for partial string matching.

### Step 2: Case insensitivity
* **Description:** The case of the characters in the input query must not affect the search results.
* **Acceptance Criteria:** For the query `"wro"` (lowercase), the system must correctly match and return the city `"Wrocław"`.
* **Implementation Tips:** Use .toLowerCase() method before comparing strings.

### Step 3: Query length validation (fail-fast)
* **Description:** For performance reasons, the search engine must not process overly short queries. If a query is too short, the system terminates immediately.
* **Acceptance Criteria:** If the query has **fewer than 2 characters** (e.g., `"x"`), the system must immediately return an empty list.
* **Implementation Tips:** Add defensive validation at method entry.

### Step 4: Alphabetical sorting
* **Description:** Search results should be sorted alphabetically by default, regardless of the order in which the cities are stored in the database.
* **Acceptance Criteria:** For the query `"ar"`, the cities *Warszawa* and *Ankara* match. The system must return them in the correct alphabetical order: `["Ankara", "Warszawa"]`.
* **Implementation Tips:** Add a sorting operation to the stream pipeline with case-insensitive comparison.

### Step 5: Limiting results
* **Description:** To avoid overwhelming the user interface, the search engine can return a maximum of 3 results at a time. Any excess matches must be cut off.
* **Acceptance Criteria:** For the query `"an"`, 5 cities from the database match. The system must limit the output and return a list of **exactly 3 elements**.
* **Implementation Tips:** Apply the limit after sorting, not before.

### Step 6: Multi-word support (tokenization)
* **Description:** For multi-word cities, the user should be able to enter the words in any order. A city is considered a match if it contains all the entered words (tokens).
* **Acceptance Criteria:** For the query `"Angeles Los"`, the system must successfully identify and return the city `"Los Angeles"`.
* **Implementation Tips:**
  - `String.split("\\s+")` - split query into tokens
  - `Arrays.stream(tokens).allMatch(...)` - verify all tokens present

### Step 7: Ignoring national characters (normalization)
* **Description:** The search engine must be resilient to the absence of diacritics (local accents, tails, or slashes). Searching with basic ASCII characters should successfully find cities with localized names.
* **Acceptance Criteria:** For the query `"wroclaw"`, the system must correctly recognize and return the city `"Wrocław"`.
* **Implementation Tips:**
  - `StringUtils.stripAccents(text)` - remove all diacritics and accents (from Apache Commons Lang)

### Step 8: Relevance scoring
* **Description:** The system must prioritize the results. Cities that **start with** the input query (prefixes) have higher priority and must appear higher on the list than cities that contain the query in the middle of their name.
* **Acceptance Criteria:** For the query `"za"`, the cities *Zakopane* (start) and *Warszawa* (middle) match. The system must return them in the exact order: `["Zakopane", "Warszawa"]`. In case of a tie in score, alphabetical order decides.
* **Implementation Tips:**
  - `Integer.compare(score2, score1)` - compare scores in descending order (higher scores first)
  - `String.compareToIgnoreCase()` - alphabetical fallback on equal scores

### Step 9: Fuzzy search fallback
* **Description:** If a user makes a minor typo and the standard search (Steps 1-8) yields zero results, the system should run an emergency fallback algorithm that tolerates errors.
* **Acceptance Criteria:** For the query `"Warszwa"` (missing the letter 'a'), standard search yields 0 results. Since the query has **at least 4 characters**, the system fires up a fuzzy search with a tolerance of **maximum 1 typo** (Levenshtein distance $\le$ 1) and successfully returns `"Warszawa"`.
* **Implementation Tips:**
  - `LevenshteinDistance.getDefaultInstance()` - create Levenshtein distance calculator (from Apache Commons Text)
  - `.apply(city, query)` - calculate edit distance between strings

### Step 10: Exclusion filter / negative search (The "-" operator)
* **Description:** Advanced users want the ability to exclude specific words from the search results by prefixing a token with a minus sign (e.g., searching for "an" but excluding anything that contains "san").
* **Acceptance Criteria:** For the query `"an -san"`, the system must return `["Ankara", "Los Angeles", "Zakopane"]` (cutting out "San Francisco" and "Santiago").
* **Implementation Tips:**
  - Split tokens into inclusion tokens (no "-" prefix) and exclusion tokens (with "-" prefix)
  - City must contain all inclusion tokens and not contain any exclusion tokens
