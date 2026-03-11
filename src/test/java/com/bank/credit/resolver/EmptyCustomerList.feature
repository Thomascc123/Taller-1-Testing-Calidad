Feature: List all Customers Validation
    Background:
        * url 'http://localhost:8088'

    Scenario: Empty List Retrieval Sucessfully
        Given path 'graphql'
        And header Content-Type = 'application/json'
        And text query =
        """
        query {
            allCustomers{
                customerId
                name
                lastName
            }
        }
        """
        And request { query: '#(query)' }
        When method post
        Then status 200
        And match response.data.allCustomers != null
        And match response.data.allCustomers == []