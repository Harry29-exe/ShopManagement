<script lang="ts">
    import {PurchaseInvoiceDTO} from "../../dto/PurchaseInvoiceDTOs";

    export let invoice: PurchaseInvoiceDTO;
</script>

<table class="md:w-[70%] w-full">
    <tbody>
    <tr>
        <th>Invoice id</th>
        <td>{invoice.id}</td>
    </tr>
    <tr>
        <th>Buyer entity</th>
        <td>{invoice.contractor.entityName}</td>
    </tr>
    <tr>
        <th>Purchaser fullname</th>
        <td>{invoice.purchaserFullname}</td>
    </tr>
    <tr>
        <th>Issue date</th>
        <td>{invoice.issueDate}</td>
    </tr>
    <tr>
        <th>Is payed</th>
        <td>{invoice.isPayed}</td>
    </tr>
    </tbody>
</table>

<style>
    th, td {
        @apply text-left border-y-2 border-black-alpha-200;
    }
</style>