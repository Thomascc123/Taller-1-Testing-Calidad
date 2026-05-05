Feature: Finding Customer Validation
    Background:
        * url 'http://localhost:8088'

    Scenario: Customer retrieval sucessfully
        Given path 'graphql'
        And header Content-Type = 'application/json'
        And text query =
        """
        query {
            customerById(customerId: 1){
                customerId
                name
                lastName
                email
                phoneNumber
                documentType
                documentNumber
                status
            }
        }
        """
        And request { query: '#(query)' }
        When method post
        Then status 200
        And match response.data.customerById == 
        """
        {
            customerId: '1',
            name: 'Camilo',
            lastName:'Perez',
            email: 'camilop@gmail.com',
            phoneNumber: '3154238456',
            documentType: 'CEDULA_CIUDADANIA',
            documentNumber: '1057452245',
            status: 'Activo'
        }
        """
    
    Scenario: Customer retrieval failed: Customer not found
        Given path 'graphql'
        And header Content-Type = 'application/json'
        And text query =
        """
        query {
            customerById(customerId: 2){
                customerId
                name
                lastName
                email
                phoneNumber
            }
        }
        """
        And request { query: '#(query)' }
        When method post
        Then status 200
        And match response.errors != null
        And match response.errors == '#[1]'
        And match response.errors[0].message == 'Cliente no encontrado'
        And match response.errors[0].extensions.code == 'CUSTOMER_NOT_FOUND'
        And match response.errors[0].extensions.status == 404
        And match response.errors[0].extensions.classification == 'BAD_REQUEST'    