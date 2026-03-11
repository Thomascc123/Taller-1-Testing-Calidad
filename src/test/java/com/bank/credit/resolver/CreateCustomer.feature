Feature: Customer creation validation
    Background:
        * url 'http://localhost:8088'

    Scenario: Customer creation sucessfully
        Given path 'graphql'
        And header Content-Type = 'application/json'
        And text mutationQuery =
        """
        mutation {
            addCustomer(
                name: "Camilo"
                lastName: "Perez"
                email: "camilop@gmail.com"
                phoneNumber: "3154238456"
                documentType: "Cédula de ciudadanía"
                documentNumber: "10186523487"
            ){
                customerId
                name
                lastName
                email
                phoneNumber
            }
        }
        """
        And request { query: '#(mutationQuery)' }
        When method post
        Then status 200
        And match response.data.addCustomer == 
        """
        {
            customerId: '#regex \\d+',
            name: 'Camilo',
            lastName:'Perez',
            email: 'camilop@gmail.com',
            phoneNumber:'3154238456'
        }
        """
    
    Scenario: Customer creation failed: invalid document Type
        Given path 'graphql'
        And header Content-Type = 'application/json'
        And text mutationQuery =
        """
        mutation {
            addCustomer(
                name: "Camilo"
                lastName: "Perez"
                email: "camilop@gmail.com"
                phoneNumber: "3154238456"
                documentType: "Documento"
                documentNumber: "10186523487"
            ){
                customerId
                name
                lastName
                email
                phoneNumber
            }
        }
        """
        And request { query: '#(mutationQuery)' }
        When method post
        Then status 200
        And match response.errors != null
        And match response.errors == '#[1]'
        And match response.errors[0].message == 'Tipo de documento no válido'
        And match response.errors[0].extensions.code == 'INVALID_DOCUMENT_TYPE'
        And match response.errors[0].extensions.status == 400
        And match response.errors[0].extensions.classification == 'BAD_REQUEST'
       