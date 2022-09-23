#Diplomska
Tekstot na diplomskata e vo word file-ot na vrvot na directoryto.

Potrebni infrastrukturni dependencies:
- Java 11
- Maven 3.6.3
- npm: 8.1.2
- node: 17.1.0

Za da se pushti aplikacijata prvo treba da se izbilda so slednite komandi:
- mvn clean install na root maven modulot (go bilda java backend-ot)
- npm install vo /my-app (go bilda frontend-ot)

Odkoga kje bide izbildana aplikacijata se pushta so
- run configuracijata DiplomskaApplication za da se startne backend-ot.
- npm start od folderot /my-app 

Odkoga kje se pushtat backend-ot i frontend-ot aplikacijata e dostapna na localhost:3000 
