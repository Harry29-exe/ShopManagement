<script lang="ts">
    import {NewSoldItemDTO} from "../../../dto/SalesInvoiceDTOs";
    import {AppMessage, popupStore} from "../../../stores/PopupStore";
    import {CreatePurchaseInvoiceRequest, PurchaseInvoiceClient} from "../../../apiclient/PurchaseInvoiceClient";
    import {NewPurchasedItemDTO} from "../../../dto/PurchaseInvoiceDTOs";

    let purchaserId: number;
    let businessEntityId: number;
    let issuedAt: Date;
    let items: NewPurchasedItemDTO[] = [];

    function addNewItem() {
        items.push(new NewSoldItemDTO())
        items = items;
    }

    async function onSubmit() {
        console.log('we')
        let result = await PurchaseInvoiceClient
            .create(new CreatePurchaseInvoiceRequest(
                purchaserId,
                businessEntityId,
                issuedAt,
                items
            ));

        if (result.ok && result.result) {
            popupStore.setNew(AppMessage.success("Invoice has been created"))
        } else {
            popupStore.setNew(result.msg)
        }
    }
</script>


<div class="widget-lg w-container-md v-stack px-1 md:px-[5%]">
    <p class="header-lg text-left w-full">Add new purchase invoice</p>

    <div class="grid-2">
        <div class="input-wrapper-sm">
            <p>Purchaser id</p>
            <input bind:value={purchaserId} type="number"/>
        </div>

        <div class="input-wrapper-sm">
            <p>Business entity id</p>
            <input bind:value={businessEntityId} type="number"/>
        </div>

        <div class="input-wrapper-sm">
            <p>Issued At</p>
            <input bind:value={issuedAt} type="date"/>
        </div>
    </div>


    <p class="header-md text-left w-full">Products</p>
    {#each items as item}
        <div class="widget-md grid-2 border-b-2 w-full">
            <div class="input-wrapper-sm">
                <p>Item id</p>
                <input bind:value={item.itemId}/>
            </div>
            <div class="input-wrapper-sm">
                <p>Quantity</p>
                <input bind:value={item.quantity} type="number"/>
            </div>
        </div>
    {/each}
    <button class="btn-md w-1/2" on:click={addNewItem}>
        +
    </button>

    <div class="flex flex-row w-full mt-10">
        <div style="flex-grow: 5"></div>
        <button on:click={onSubmit}
                class="btn-success-md relative mr-0 ml-auto">
            Submit
        </button>
    </div>

</div>