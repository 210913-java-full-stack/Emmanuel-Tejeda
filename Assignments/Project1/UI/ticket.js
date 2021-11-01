
$.get('http://localhost:8080/Project1/ticketKiosk', function(data) {
            // console.log(data);
            servStartingCity = data.startingCity;

            for (var i = 0; i <data.length; i++) {  

            let myTable = document.querySelector('#table');

            let employees = [
                { flightID: data[i].flightID , ticketID: data[i].ticketID, 
                    customerID: data[i].customerID, customerLastName: data[i].customerLastName,
                    customerName: data[i].customerName, isCheckedIn: data[i].isCheckedIn},
            
            ]

            let headers = ['flight ID', 'Ticket ID', 'Customer ID', 'Customer Last Name',
                            'Customer Name', 'Checked in Status'];

            
            let table = document.createElement('table');
                        let headerRow = document.createElement('tr');
                        headers.forEach(headerText => {
                            let header = document.createElement('th');
                            let textNode = document.createTextNode(headerText);
                            header.appendChild(textNode);
                            headerRow.appendChild(header);
                        });

                        table.appendChild(headerRow);
                        employees.forEach(emp => {
                            let row = document.createElement('tr');
                            Object.values(emp).forEach(text => {
                                let cell = document.createElement('td');
                                let textNode = document.createTextNode(text);
                                cell.appendChild(textNode);
                                row.appendChild(cell);
                            })
                            table.appendChild(row);
                        });
                        myTable.appendChild(table);
                    }




});
