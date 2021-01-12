document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          if(this.validateForm()){
            this.currentStep++;
            this.updateForm();
          }
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));

      //Disallow enter
      form.addEventListener("keydown", e => {
        if(e.keyCode===13){
          e.preventDefault();
          if(this.validateForm()){
            this.currentStep++;
            this.updateForm();
          }
        }
      });
    }


    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation - DONE

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary - DONE
      if (this.currentStep === 5) {
        //step 1
        const categories = form.querySelectorAll("[name=categories]:checked ~ .description");
        let checkedCategories = [];
        categories.forEach(category => {checkedCategories.push(category.innerText);});
        this.$form.querySelector("#summaryCategories").innerText = "gdzie spakowałeś: " + checkedCategories.join(", ");
        //step 2
        this.$form.querySelector("#summaryQuantity").innerText = this.$form.querySelector("#quantity").value;
        //step 3
        const checkedInstitution = this.$form.querySelector("[name=institution]:checked").parentElement.querySelector("div.title").innerText;
        this.$form.querySelector("#summaryInstitution").innerText = checkedInstitution;
        //step4
        this.$form.querySelector("#summaryStreet").innerText = this.$form.querySelector("#street").value;
        this.$form.querySelector("#summaryCity").innerText = this.$form.querySelector("#city").value;
        this.$form.querySelector("#summaryZipCode").innerText = this.$form.querySelector("#zipCode").value;
        this.$form.querySelector("#summaryPhoneNumber").innerText = this.$form.querySelector("#phoneNumber").value;
        this.$form.querySelector("#summaryPickUpDate").innerText = this.$form.querySelector("#pickUpDate").value;
        this.$form.querySelector("#summaryPickUpTime").innerText = this.$form.querySelector("#pickUpTime").value;
        this.$form.querySelector("#summaryPickUpComment").innerText = this.$form.querySelector("#pickUpComment").value;
      }

    }

    validateForm() {
      if (this.currentStep === 1) {
        if (!this.validateStep1()) {
          return false;
          }
      }
      if (this.currentStep === 2) {
        if (!this.validateStep2()) {
          return false;
        }
      }
      if (this.currentStep === 3) {
        if (!this.validateStep3()) {
          return false;
        }
      }
      if (this.currentStep === 4) {
        if (!this.validateStep4()) {
          return false;
        }
      }
      this.currentStep++;
      this.updateForm();

    }

    validateStep1() {
      const categories = form.querySelectorAll("[name=categories]:checked");
      if (categories.length === 0) {
        alert("Musisz zaznaczyć co najmniej jedno z pól wyboru.");
        return false;
      }
      return true;
    }

    validateStep2() {
      let quantity = this.$form.querySelector("#quantity").value;
      if (!isFinite(quantity) || quantity.length===0 || quantity<=0) {
        alert("Musisz wprowadzić poprawną ilośc worków.");
        return false;
      }
      return true;
    }

    validateStep3() {
      if (!this.$form.querySelector("[name=institution]:checked")) {
        alert("Musisz wybrać adresata z listy.");
        return false;
      }
      return true;
    }

    validateStep4() {
      let street = this.$form.querySelector("#street").value;
      const regExpStreet = new RegExp("^[A-z0-9śŚłŁżŻźŹćĆóÓąĄęĘńŃ]+([\\s-]?[A-z0-9śŚłŁżŻźŹćĆóÓąĄęĘńŃ]+)*\\s([0-9]+([\\/m]?))?\\d+$");

      let city = this.$form.querySelector("#city").value;
      const regExpCity = new RegExp("^[A-zśŚłŁżŻźŹćĆóÓńŃ]+([\\s-]?[A-z0-9śŚłŁżŻźŹćĆóÓąĄęĘńŃ]+){0,3}");

      let zipCode = this.$form.querySelector("#zipCode").value;
      const regExpZipCode = new RegExp("^[0-9]{2}-[0-9]{3}");

      let phoneNumber = this.$form.querySelector("#phoneNumber").value;

      let pickUpDate = this.$form.querySelector("#pickUpDate").value;
      const regExpDate = new RegExp("^2\\d{3}-[0-2]\\d-[0-3]\\d");

      let pickUpTime = this.$form.querySelector("#pickUpTime").value;
      const regExpTime = new RegExp("^([01]?\\d|2[0-3]):[0-5]\\d");

      let pickUpComment = this.$form.querySelector("#pickUpComment").value;


      if (street.length === 0 || !regExpStreet.test(street)) {
        alert("Musisz podać poprawną nazwę ulicy i numer domu/lokalu");
        return false;
      }
      if(city.length === 0 || !regExpCity.test(city)){
        alert("Musisz podać poprawną nazwę miasta");
        return false;
      }
      if (zipCode.length === 0 || !regExpZipCode.test(zipCode)) {
        alert("Musisz podać poprawny format kodu pocztowego [00-000]");
        return false;
      }

      if(phoneNumber.length === 0 || !isCorrectPhoneNumber(phoneNumber)){
        alert("Musisz podać poprawny numer telefonu");
        return false;
      }

      if (pickUpDate.length === 0 || !regExpDate.test(pickUpDate)) {
        alert("Musisz podać datę w formacie rrrr-MM-dd np. 2010-07-13");
        return false;
      }else {
        let today=new Date()
        if (today>=new Date(pickUpDate)){
          alert("Musisz podać datę z przyszłości");
          return false;
        }
      }

      if(pickUpTime.length === 0 || !regExpTime.test(pickUpTime)){
        alert("Musisz podać czas odbioru w formacie HH:mm np. 14:20");
        return false;
      }
      if(pickUpComment.length!==0){
        if(pickUpComment.includes('>')) {
          alert("Użyto niedozwolonych znaków");
          return false;
        }
        return true;
      }
      return true;


      function isCorrectPhoneNumber(telNumber) {
        const regExpPhoneNumber1 = new RegExp("^([0-9]{3})([\s-]?[0-9]{3}){2}");
        const regExpPhoneNumber2 = new RegExp("^00[0-9]{2}([\s-]?[0-9]{3}){3}");
        const regExpPhoneNumber3 = new RegExp("^\\+[0-9]{2}([\s-]?[0-9]{3}){3}");
        return regExpPhoneNumber1.test(telNumber) || regExpPhoneNumber2.test(telNumber) || regExpPhoneNumber3.test(telNumber);
      }
    }
}

  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
