Feature: Grant Credit to a Customer Validation
    Background:
        * url 'http://localhost:8088'

    Scenario: Credit Granted Sucessfully (Active and not Defaulter Customer)
        Given path 'graphql'
        And header Content-Type = 'application/json'
        And text mutationQuery =
        """
        mutation{
            grantCredit(
                customerId: 1
                type: "Consumo"
                amount: 2500000.0
                paymentsNumber: 36
            ){
                creditId
                customerId
                type
                amount
                status
            }
        }
        """
        And request { query: '#(mutationQuery)' }
        When method post
        Then status 200
        And match response.data.grantCredit ==
        """
        {
            creditId: '#regex \\d+',
            customerId: '1',
            type: 'Consumo',
            amount: 2500000.0,
            status: 'Activo'
        }
        """
    
    Scenario: Credit Granted Failed (Inactive Customer)
        Given path 'graphql'
        And header Content-Type = 'application/json'
        And text mutationQuery =
        """
        mutation{
            grantCredit(
                customerId: 2
                type: "Educativo"
                amount: 16000000.0
                paymentsNumber: 48
            ){
                creditId
                customerId
                type
                amount
                status
            }
        }
        """
        And request { query: '#(mutationQuery)' }
        When method post
        Then status 200
        And match response.errors != null
        And match response.errors == '#[1]'
        And match response.errors[0].message == 'Cliente inactivo'
        And match response.errors[0].extensions.code == 'INACTIVE_CUSTOMER'
        And match response.errors[0].extensions.status == 409
        And match response.errors[0].extensions.classification == 'BAD_REQUEST'
    
    Scenario: Credit Granted Failed (Defaulter Customer)
        Given path 'graphql'
        And header Content-Type = 'application/json'
        And text mutationQuery =
        """
        mutation{
            grantCredit(
                customerId: 3
                type: "Hipotecario"
                amount: 65000000.0
                paymentsNumber: 120
            ){
                creditId
                customerId
                type
                amount
                status
            }
        }
        """
        And request { query: '#(mutationQuery)' }
        When method post
        Then status 200
        And match response.errors != null
        And match response.errors == '#[1]'
        And match response.errors[0].message == 'Cliente en mora'
        And match response.errors[0].extensions.code == 'DEFAULTER_CUSTOMER'
        And match response.errors[0].extensions.status == 409
        And match response.errors[0].extensions.classification == 'BAD_REQUEST'

