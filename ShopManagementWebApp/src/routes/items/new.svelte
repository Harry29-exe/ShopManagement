<script lang="ts">

    import {CreateItemRequest, ItemClient} from "../../apiclient/ItemClient";
    import {AppMessage, popupStore} from "../../stores/PopupStore";

    let name: string;
    let codeName: string;
    let description: string;
    let price: number;
    let taxRate: number;

    async function onSubmit() {
        let response = await ItemClient.create(new CreateItemRequest(
            name, codeName, description, price, (taxRate / 100)
        ));

        if (response.ok) {
            popupStore.setNew(AppMessage.success("Item has been created"));
        } else {
            popupStore.setNew(response.msg)
        }
    }
</script>

<div class="widget-lg w-container-md v-stack px-1 md:px-[5%]">
    <p class="header-lg">Add item</p>

    <div class="input-wrapper-sm">
        <p>Name</p>
        <input bind:value={name}/>
    </div>

    <div class="input-wrapper-sm">
        <p>Code name</p>
        <input bind:value={codeName}/>
    </div>

    <div class="input-wrapper-sm">
        <p>Description</p>
        <input bind:value={description}/>
    </div>

    <div class="input-wrapper-sm">
        <p>Price</p>
        <input bind:value={price} type="number"/>
    </div>

    <div class="input-wrapper-sm">
        <p>Tax rate [%]</p>
        <input bind:value={taxRate} type="number"/>
    </div>

    <button on:click={onSubmit}
            class="btn-success-md my-4">
        Submit
    </button>

</div>