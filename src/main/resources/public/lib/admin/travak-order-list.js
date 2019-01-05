import DenTravakAbstractElement from '../travak-abstract-element.js';

class DenTravakOrderList extends DenTravakAbstractElement {

    constructor() {
        super('travak-admin-app')
    }

    connectedCallback() {
        super.connectedCallback();
        fetch('/den-travak/orders/')
            .then(resp => resp.json())
            .then(json => this.updateOrderList(json));
        this.initEventListeners();
    }

    initEventListeners() {
        this.byId('edit-sandwiches-btn').addEventListener('click', (e) => this.app().showSandwichList());
        this.byId('download-orders-btn').addEventListener('click', (e) => this.toCSV());
    }

    updateOrderList(orders) {
        let orderList = this.byId('orders');
        orderList.innerHTML = ``;
        orders.forEach(order => {
            let orderEl = htmlToElement(this.getOrderTemplate(order));
            orderList.appendChild(orderEl);
        });
    }

    updateOrdersPrint(){
        fetch('/den-travak/orders/')
            .then((resp) => resp.json())
            .then(function(data) {
                let orders = data;
                orders.forEach(order => {
                    fetch(`/den-travak/orders?printed=${order.id}`, {
                        method: "PUT",
                        headers: {
                            "Content-Type": "application/json; charset=UTF-8"
                        }
                    });
                });
            });
    }

    toCSV(){
        this.updateOrdersPrint();
        fetch('/den-travak/orders/')
            .then(resp => resp.json())
            .then(json =>
        {
            this.updateOrderList(json);
            let items = [];
            items.push(["ID", "Phone number", "Breadtype", "Price", "Order Date" ]);
            json.forEach(j => {
                items.push([j.id, j.mobilePhoneNumber, j.breadType, j.price, j.creationDate]);
            });
            console.log(items);
            let csv = "";
            items.forEach(d => {
                let row = d.join(",");
                csv += row + "\r\n";
            })

            var link = document.createElement('a');
            link.setAttribute('type', 'hidden');
            link.setAttribute('href','data:text/csv;charset=utf-8,' + encodeURI(csv) );
            link.setAttribute('download','printorders.csv' );
            document.body.appendChild(link);
            link.click();
        });


    }

    get template() {
        return `
            <style>
                div.dt-order-info {
                    margin-left: auto;
                }
                .bmd-list-group-col {
                    width: 70%;
                }
                p.list-group-item-heading {
                    display:flex;
                    justify-content: space-between;
                }
                span.creationDate {
                    display:inline-block;
                    float: right;
                }
                .travak-header {
                    display: flex;
                }
                .travak-header button {
                    margin-left: auto;
                }
            </style>
            <div class="animate">
                <div class="travak-header">
                    <h4>Den Travak Bestellingen</h4>
                    <button id="download-orders-btn" type="button" class="btn btn-primary">Download orders</button>
                    <button id="edit-sandwiches-btn" type="button" class="btn btn-primary">Bewerk broodjeslijst</button>
                </div>
                <div>
                <ul id="orders" class="list-group">
                </ul>
                </div>
            </div>
        `;
    }

    getOrderTemplate(order) {
        return `
            <a class="list-group-item">
                <button type="button" class="btn btn-primary bmd-btn-fab">
                    ${order.name.charAt(0)}
                </button>
                <div class="bmd-list-group-col">
                    <p class="list-group-item-heading">${order.mobilePhoneNumber}<span class="creationDate">${dateFns.distanceInWordsToNow(order.creationDate)} ago</span></p>
                    <p class="list-group-item-text">${order.name} - ${order.breadType.toLowerCase()}</p>
                </div>
                <div class="dt-order-info">
                    <p class="list-group-item-text">${order.price} </p>
                </div>
                <div class="dt-order-info">
                    <p class="list-group-item-text">${order.printed}</p>
                </div>
            </a>
        `;
    }
}

customElements.define('travak-order-list', DenTravakOrderList);