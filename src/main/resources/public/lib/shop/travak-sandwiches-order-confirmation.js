import DenTravakAbstractElement from '../travak-abstract-element.js';

class DenTravakSandwichesOrderConfirmation extends DenTravakAbstractElement {

    connectedCallback() {
        super.connectedCallback();
        this.initEventListeners();
    }

    init(order) {
        this.order = order;
    }

    initEventListeners() {
        this.byId('show-sandwich-list').addEventListener('click', e => this.app().dispatchEvent(new Event('show-sandwich-list')));
        this.shadowRoot.querySelectorAll('button.rating-buttons')
            .forEach(btn => {
                btn.addEventListener('click', event => {
                    console.log('selected rating', btn.dataset.score);
                    let recommendation = {};
                    recommendation.emailAddress = this.order.mobilePhoneNumber;
                    recommendation.ratedItem = this.order.sandwichId;
                    recommendation.rating = btn.dataset.score;

                    fetch('/recommendation/recommend/', {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json; charset=UTF-8"
                        },
                        body: JSON.stringify(recommendation)
                    }).then(response => {
                        if (response.ok) {
                            this.app().dispatchEvent(new CustomEvent('show-sandwich-list', {detail: recommendation}));
                            alert('Thanks for the rating')
                        } else {
                            alert('An error occurred')
                        }
                    });
                });
            });
    }

    get template() {
        return `
            <style>
                .form-group {
                    margin-bottom: 2rem!important;
                }
                .dt-header {
                    display: flex;
                }
                .dt-header button {
                    margin-left: auto;
                }
                div.dt-sandwich-info {
                    margin-left: auto;
                }
            </style>
            <div class="animate">
                <div class="dt-header">
                    <h3>Welkom bij den Travak</h3>
                    <button id="show-sandwich-list" type="button" class="btn btn-primary">Nieuwe bestelling</button>
                </div>
                <h4>Bedankt!</h4>
                <p>Wij hebben je bestelling goed ontvangen en je kan je broodje komen ophalen vanaf 11u45.</p>
                <p>Tot zo dadelijk!</p>
                
                <p>Geef een rating:</p>
                <button type="button" class="btn btn-primary bmd-btn-fab rating-buttons" data-score="1">1</button>
                <button type="button" class="btn btn-primary bmd-btn-fab rating-buttons" data-score="2">2</button>
                <button type="button" class="btn btn-primary bmd-btn-fab rating-buttons" data-score="3">3</button>
                <button type="button" class="btn btn-primary bmd-btn-fab rating-buttons" data-score="4">4</button>
                <button type="button" class="btn btn-primary bmd-btn-fab rating-buttons" data-score="5">5</button>
            </div>
        `;
    }
}

customElements.define('travak-sandwiches-order-confirmation', DenTravakSandwichesOrderConfirmation);