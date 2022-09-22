import React, {useRef} from "react";
import {Link} from "react-router-dom";
import {useHistory} from "react-router-dom";
import search from "../../images/search.png";
import {TextField} from "@mui/material";

const SearchByFat = (props) => {
    const minFatRef = useRef('');
    const maxFatRef = useRef('');
    let history = useHistory();

    const formSubmissionHandler = event => {
        event.preventDefault();
        history.push("/");
        props.onParentCallBackForSearchByFat(minFatRef.current.value, maxFatRef.current.value)
    }

    return (
        <form onSubmit={formSubmissionHandler}>
            <Link style={{textDecoration: "none", cursor: "default",marginRight:242}}>Search by Fat</Link>
            <input style={{width: "150px", marginLeft: "30px", marginRight: "5px"}} ref={minFatRef}
                   defaultValue={0}/>
            <input style={{width: "150px"}} ref={maxFatRef}/>
            {/*<TextField id="standard-basic" label="Minimum fat" variant="standard" style={{width:"150px"}}  ref={minFatRef} />*/}
            {/*<TextField id="standard-basic" label="Maximum fat" variant="standard" style={{width:"150px"}}   ref={maxFatRef}/>*/}
            <button type="submit" className="search-button">
                <img src={search} alt="Magnifying glass"/>
            </button>
        </form>
    )


}

export default SearchByFat;