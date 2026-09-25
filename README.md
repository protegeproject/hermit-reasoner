# HermiT

[![Build](https://github.com/protegeproject/hermit-reasoner/actions/workflows/build.yml/badge.svg)](https://github.com/protegeproject/hermit-reasoner/actions/workflows/build.yml)

HermiT is a conformant OWL 2 DL reasoner that uses the direct semantics. It
supports all OWL 2 DL constructs and the datatypes required by the OWL 2
specification.

## License

HermiT is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
   
HermiT is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.
   
Copies of the GNU General Public License and the GNU Lesser General Public
License are included in [`gpl.txt`](gpl.txt) and
[`lgpl-3.0.txt`](lgpl-3.0.txt), respectively. The licenses are also available
from the [GNU website](https://www.gnu.org/licenses/).

More information about HermiT and additional licensing information is
available from the [HermiT website](http://hermit-reasoner.com), or by
contacting Boris Motik or Ian Horrocks at the Department of Computer Science in
the University of Oxford.

## Bundled libraries

HermiT uses the following libraries in unmodified form:

1. [dk.brics.automaton](http://www.brics.dk/automaton/), copyright © 2001–2009
   Anders Møller, released under the BSD license.
2. [JAutomata](http://jautomata.sourceforge.net/), released under LGPL 2.1.
3. [The OWL API](http://owlapi.sourceforge.net), released under LGPL 3.0.
4. [Apache Axiom](https://ws.apache.org/axiom/), released under the Apache
   License 2.0.
5. [Apache Commons Logging](https://commons.apache.org/logging/), released under
   the Apache License 2.0.

The corresponding license files are available in `project/lib` or in the
Protégé plugin JAR.

## Building

The Maven build produces `hermit-reasoner-<version>.jar`. It can be used from the
command line, as a library in other Java programs, or as a plug-in for Protégé 6.
The source tree contains the HermiT implementation, tests, and examples.

## Publishing a release

Releases are published to Maven Central by the GitHub Actions publishing
workflow. Before creating a release:

1. Make sure the Protégé dependency in the `protege6` Maven profile refers to a
   published, non-SNAPSHOT Protégé 6 release.
2. Run the full test suite and merge the release changes into `main`.
3. Create and publish a GitHub release from the intended commit. Use the Maven
   version as the tag, for example `1.5.0`, without a `v` prefix.
4. Check that the Publish packages workflow succeeds, then verify the artifacts
   in Maven Central and the generated download URL in `update.properties`.

The workflow sets the Maven project version from the release tag, signs the
artifacts, publishes the JAR, sources, Javadocs, and POM, and updates
`update.properties` on `main` with the release version and URLs. The metadata is
only updated after publication succeeds. A manually dispatched workflow
publishes the current SNAPSHOT version without changing `update.properties`.

## Release history

### HermiT 1.0

- supports all of OWL 2

### HermiT 1.1

- supports DL-safe SWRL rules

### HermiT 1.2

- contains a novel blocking strategy
- minor bug fixes (nominals and equality statements in the ABox were not handled 
  correctly in some case)
### HermiT 1.2.1

- minor bug fixes (nominals and equality statements in the ABox were still not 
  handled correctly if the ontology was nondeterministic, custom data ranges were not 
  loaded correctly in some case)
### HermiT 1.2.2

- minor bug fixes (complex concept queries didn't work properly in the 
  presence of role chains and transitivity), added a progress monitor for realisation
  that is used by Protege
### HermiT 1.2.3

- several bug fixes (getDisjointObjectProperties(...) and 
  getDisjointDataProperties(...) were not terminating, a null pointer exception in 
  getDataPropertyValues(), language tags were case sensitive, ontology change listener 
  was not removed on dispose), new concept classification algorithm for 
  non-deterministic ontologies, new object property classification, HermiT will now 
  always track changes even when not instantiated as an OWLReasoner, Protege reasoner 
  preferences will be taken into account for the next Protege release   
### HermiT 1.2.4

- some bug fixes (bottomObjectProperty encoding corrected, blocking cache cached 
  inactive nodes under single blocking, correct INF/-INF parsing for doubles, no empty 
  node sets in case the next node in the property hierarchy has only inverses), 
  improved property instance retrieval, new disjunction ordering strategy, negative 
  object property assertion encoding no longer requires nominals, command line 
  outputs full IRIs instead of abbreviated ones with HermiT's prefixes, apply changes 
  in non-buffering mode only when needed for a query, data values are stored also 
  with their lexical form, new data property classification
### HermiT 1.2.5

- improved instance handling (class and object property instances, sameAs), new OWL 
  API 3.1, never officially release, but shipped with Protege's beta test version
  for the new OWL API 
### HermiT 1.3.0

- as the inofficial 1.2.5, fixed a null pointer exception in the instance manager
### HermiT 1.3.1

- fixed a bug that caused HermiT to return equivalent properties instead of inverse 
  properties in the method getInverseObjectProperties, automata for complex roles 
  are no longer determinised to safe time, extended explanation example to cover
  inconsistency explanation, improved disjoint classes code in the materialisation 
  example, anonymous individuals are no longer allowed in OneOf constructs (OWL 2 
  DL conformance)
### HermiT 1.3.2

- fixed a bug in the classification (in the case of several unsatisfiable classes, 
  HermiT skipped satisfiability tests for some of them and wrongly considered them 
  satisfiable), fixed a bug in object property instance retrieval that left out 
  related individuals if merging has occurred due to number restrictions, made 
  instance manager work also with unknown individuals (fresh entities), complex 
  data ranges are no longer simulated via concepts, but have their own 
  representations to fix a bug with rdfs:Literal
### HermiT 1.3.3

- New bug-fix release of the OWL API is used (3.2.1). Added a method to just write 
  out axioms for the transitively reduced (class/object property/dataproperty) 
  hierarchy, much faster than pretty printing but no longer a complete ontology 
  (missing header and declarations) and not nicely ordered and indented. Only 
  equivalent classes (properties) are ordered in the according axiom. The 
  command line by default now just writes out the axioms, but pretty printing can 
  be enforced with the --prettyPrint argument. Command line interface has been 
  slightly tidied up and extended.  
  precomputeInferences() now silently ignores unsupported tasks and disjointClasses 
  and data property assertions are no longer precomputable via the OWLReasoner 
  interface (disj. classes too slow, data property assertions are anyway only using 
  the data property hierarchy and sameAs individuals). Disj. classes can still be 
  precomputed when using HermiT's native interface (Reasoner).    
  Fixed a bug that sometimes made sameAs computation hang.
### HermiT 1.3.4

- New OWL API is used (3.2.2). Incremental loading and unloading of simple ABoxes
  (only assertions without complex classes). Fixed an error in the equals method 
  of DateTime (no more unsafe casts), and an error for retrieving direct types of 
  individuals. 
### HermiT 1.3.5

- Improved object property classification. Fixed a bug in the detection of clashes 
  involving InternalDatatype objects, and a bug in the instance manager.
### HermiT 1.3.6

- Improved performance of the instance manager for reading off property instances 
  by avoiding double loops. Refactored the clausification of SWRL rules. Fixed a bug 
  in instance manager to properly flush the additional ontology. Fixed a bug in the 
  implementation of the rdf:PlainLiteral datatype. Fixed a bug in the flush() method 
  to check whether the changes were relevant to the import closure of the root ontology. 
  Fixed a problem with absolute IRIs that had <> around the string as officially required, 
  which, however, causes problems in OWL API. Fixed a bug with property chain encoding.
### HermiT 1.3.7

- New OWL API is used (3.4.3). Added new statistics methods in the counting monitor. 
  Changed the Prefixes class so that it uses the terminology from the OWL 2 specification. 
  Added a new library to fix the javax.xml.stream.XMLStreamException error in Protege for 
  malformed XML literals. Improved the handling of owl:topObjectProperty when loading 
  additional axioms (e.g., for entailment checking). Fixed a bug in incremental ABox 
  updates with fresh names. Fixed a bug with core blocking. Fixed a HasKey clausification 
  bug for negated classes. Fixed a bug in the instance retrieval process and a NPE problem 
  when retirving same individuals for an individual that does not exist in the ontology. 
  Fixed a bug in the Protege plugin that is caused by a change to the Protege sources.
  Fixed some warnings in Java 1.7.
### HermiT 1.3.8

- Fixed a bug where we ignored declared classes/properties after an incremental change. 
  Fixed array index out of bound error for empty property chains.
