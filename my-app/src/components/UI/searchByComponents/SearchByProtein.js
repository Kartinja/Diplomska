import React, {useRef} from "react";
import {Link} from "react-router-dom";
import {useHistory} from "react-router-dom";
import search from "../../images/search.png";
import {TextField} from "@mui/material";

const SearchByProtein = (props) => {
    const minProteinRef = useRef('');
    const maxProteinRef = useRef('');
    let history = useHistory();

    const formSubmissionHandler = event => {
        event.preventDefault();
        history.push("/");
        props.onParentCallBackForSearchByProtein(minProteinRef.current.value, maxProteinRef.current.value)
    }

    return (
        <form onSubmit={formSubmissionHandler}>
            <Link style={{textDecoration: "none", cursor: "default", marginRight:"200px"}}>Search by Protein</Link>
            <input style={{width: "150px", marginLeft: "30px", marginRight: "5px"}} ref={minProteinRef} defaultValue={0}/>
            <input style={{width: "150px"}} ref={maxProteinRef}/>
            {/*<TextField id="standard-basic" label="Minimum Protein" variant="standard" style={{width:"150px"}} ref={minProteinRef}/>*/}
            {/*<TextField id="standard-basic" label="Maximum Protein" variant="standard" style={{width:"150px"}} ref={maxProteinRef}/>*/}
            <button type="submit" className="search-button" >
                <img src={search} alt="Magnifying glass"/>
            </button>
        </form>
    )


}

export default SearchByProtein;