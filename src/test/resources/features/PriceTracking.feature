@melasoft
Feature: Retrieve pricing information from multiple URLs


Scenario: Navigate to Easybill URL and retrieve pricing information
Given I navigate to "https://www.easybill.de/en/pricing"
When I extract easybill pricing details


Scenario: Navigate to Zervant URL and retrieve pricing information
Given I navigate to "https://www.zervant.com/en/pricing/"
When I extract zervant pricing details


Scenario: Navigate to Buhl URL and retrieve pricing information
Given I navigate to "https://www.buhl.de/buero/wiso-meinbuero-rechnungen/#preise"
When I extract meinbuero pricing details


Scenario: Navigate to Billomat URL and retrieve pricing information
Given I navigate to "https://www.billomat.com/preise/"
When I extract billomat pricing details

Scenario: Navigate to Sevdesk URL and retrieve pricing information
Given I navigate to "https://sevdesk.de/preise/"
When I extract sevdesk pricing details

Scenario: Navigate to Fastbill URL and retrieve pricing information
Given I navigate to "https://www.fastbill.com/preise"
When I extract fastbill pricing details

Scenario: Navigate to SumUp URL and retrieve pricing information
Given I navigate to "https://www.sumup.com/de-de/preise/"
When I extract sumup pricing details


Scenario: Navigate to Lexoffice URL and retrieve pricing information
Given I navigate to "https://www.lexoffice.de/preise/"
When I extract lexoffice pricing details

Scenario: Navigate to GetMyInvoices URL and retrieve pricing information
Given I navigate to "https://www.getmyinvoices.com/de/"
When I extract getmyinvoices pricing details