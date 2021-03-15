using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using MyPrikormWebAPI.Repositories;
using MyPrikormWebAPI.DB.Entities;

namespace MyPrikormWebAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsersController : ControllerBase
    {
        private UserRepository rep;

        public UsersController(UserRepository rep)
        {
            this.rep = rep;
        }

        // GET api/user
        //[EnableCors("AnotherPolicy")]
        [HttpGet]
        public async Task<ActionResult<List<User>>> Get()
        {
            return await rep.GetAll();
        }

        // GET api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpGet("{id}")]
        public async Task<ActionResult<User>> Get(int id)
        {
            User user = await rep.Get(id);
            if (user == null)
            {
                return NotFound();
            }
            return new ObjectResult(user);
        }

        // POST api/values
        //[EnableCors("AnotherPolicy")]
        [HttpPost]
        public async Task<ActionResult<User>> Post(User user)
        {
            if (user == null)
            {
                return BadRequest();
            }

            return Ok(await rep.Create(user));
        }

        // PUT api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpPut("{id}")]
        public async Task<ActionResult<User>> Put(long id, User user)
        {
            if (user == null)
            {
                return BadRequest();
            }

            return Ok(await rep.Update(user));
        }

        // DELETE api/values/5
        //[EnableCors("AnotherPolicy")]
        [HttpDelete("{id}")]
        public async Task<ActionResult<User>> Delete(int id)
        {
            User user = await rep.Delete(id);
            if (user == null)
            {
                return NotFound();
            }
            return new ObjectResult(user);
        }
    }
}
