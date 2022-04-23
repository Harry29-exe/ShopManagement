<script lang="ts">
    import {page} from "$app/stores"
    import {onMount} from "svelte";
    import {popupStore} from "../../../stores/PopupStore";
    import {PurchaseInvoiceClient} from "../../../apiclient/PurchaseInvoiceClient";
    import {PurchaseInvoiceDTO} from "../../../dto/PurchaseInvoiceDTOs";
    import PurchaseInvoiceView from "../../../components/invoices/PurchaseInvoiceView.svelte";

    let invoiceId: number = Number.parseInt($page.params.id);
    let invoice: PurchaseInvoiceDTO;
    onMount(async () => {
        let result = await PurchaseInvoiceClient.get(invoiceId);
        if (result.ok && result.result) {
            invoice = result.result;
        } else {
            popupStore.setNew(result.msg);
        }
    })
</script>

{#if invoice}
    <div class="mt-10 w-11/12 lg:w-8/12 mx-auto">
        <PurchaseInvoiceView invoice={invoice}/>
    </div>
{/if}