<script>
    import "../app.css";
    import "../style/basic-components.css"
    import "../style/table-components.css"
    import "../style/flex-components.css"


    import Navbar from "../components/navigation/Navbar.svelte";
    import Popup from "../components/Popup.svelte";
</script>

<Navbar></Navbar>
<slot></slot>



<Popup/>