Feature: List all Customers Validation
    Background:
        * url 'http://localhost:8088'

    Scenario: Filled List Retrieval Sucessfully
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
        And match response.data.allCustomers == 
        """
        [
            {
                customerId: '1',
                name: 'Camilo',
                lastName:'Perez',
            },
            {
                customerId: '2',
                name: 'Alejandro',
                lastName:'Zuluaga',
            },
            {
                customerId: '3',
                name: 'Camila',
                lastName:'Montoya',
            }
        ]
        """  