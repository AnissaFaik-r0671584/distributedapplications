import DenTravakAbstractElement from '../travak-abstract-element.js';
import '../common/travak-input-text.js';

class DenTravakEditSandwich extends DenTravakAbstractElement {

    constructor() {
        super('travak-admin-app')
    }

    connectedCallback() {
        super.connectedCallback();
        this.initEventListeners();
    }

    initEventListeners() {
        this.byId('save-btn').addEventListener('click', e => this.saveSandwich());
        this.byId('cancel-btn').addEventListener('click', e => this.app().showSandwichList());
    }

    saveSandwich() {
        //todo: call backend via fetch api
        let sandwich = {};
        sandwich.name = this.byId('name').value;
        sandwich.price = this.byId('price').value;
        sandwich.ingredients = this.byId('ingredients').value;
        if(this.sandwich.id != null) {
            sandwich.id = this.sandwich.id;
            fetch(`/den-travak/sandwiches/${this.sandwich.id}/`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json; charset=UTF-8"
                },
                body: JSON.stringify(sandwich)
            }).then(response => {
                if (response.ok) {
                    this.app().dispatchEvent(new CustomEvent('save-succeeded', {detail: this.sandwich}));
                } else {
                    alert('An error occurred')
                }
            });
        }else{
            fetch('/den-travak/sandwiches/', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json; charset=UTF-8"
                },
                body: JSON.stringify(sandwich)
            }).then(response => {
                if (response.ok) {
                    this.app().dispatchEvent(new CustomEvent('save-succeeded', {detail: this.sandwich}));
                } else {
                    alert('An error occurred')
                }
            });
        }
    }

    init(sandwich) {
        if(sandwich) {
            this.sandwich = sandwich;
            this.byId('title').innerHTML = `Bewerk broodje ${sandwich.name}`;
            this.byId('name').value = sandwich.name;
            this.byId('ingredients').value = sandwich.ingredients;
            this.byId('price').value = sandwich.price;
        } else {
            this.sandwich = {};
            this.byId('title').innerHTML = `Maak een nieuw broodje`;
            this.byId('name').value = "";
            this.byId('ingredients').value = "";
            this.byId('price').value = "";
        }
    }

    get template() {
        return `
            <h4 id="title">Bewerk een broodje</h4>
            <travak-input-text id="name" label="Sandwich naam" placeholder="De naam van het broodje"></travak-input-text>
            <travak-input-text id="ingredients" label="Ingredienten" placeholder="Welke ingredienten heeft het broodje"></travak-input-text>
            <travak-input-text id="price" label="Prijs" placeholder="De prijs van het broodje"></travak-input-text>
            <button id="cancel-btn" type="button" class="btn">Cancel</button>
            <button id="save-btn" type="button" class="btn btn-primary">Save</button>
        `;
    }

}

customElements.define('travak-edit-sandwich', DenTravakEditSandwich);