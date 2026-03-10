Feature: Customer creation validation
    Scenario: Customer Creation
        Given url 'http://localhost:8088/graphql'
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
            customerId: '#number',
            name: 'Camilo',
            lastName:'Perez',
            email: 'camilop@gmail.com',
            phoneNumber:'3154238456'
        }
        """